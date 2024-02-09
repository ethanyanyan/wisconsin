from concurrent import futures
import grpc
import station_pb2
import station_pb2_grpc
from cassandra.cluster import Cluster, NoHostAvailable
from cassandra.query import tuple_factory
from cassandra import ConsistencyLevel, Unavailable

class StationRecord:
    def __init__(self, tmin, tmax):
        self.tmin = tmin
        self.tmax = tmax


class StationService(station_pb2_grpc.StationServicer):
    def __init__(self):
        # Initialize Cassandra connection
        self.cluster = Cluster(['p6-db-1', 'p6-db-2', 'p6-db-3'])
        self.session = self.cluster.connect('weather')
        self.session.row_factory = tuple_factory

        # Register user type for station record
        self.cluster.register_user_type('weather', 'station_record', StationRecord)

        # Prepare statements for Cassandra queries
        self.insert_statement = self.session.prepare(
            "INSERT INTO stations (id, date, record) VALUES (?, ?, ?)"
        )
        self.insert_statement.consistency_level = ConsistencyLevel.ONE

        self.max_statement = self.session.prepare(
            "SELECT MAX(record.tmax) FROM stations WHERE id = ?"
        )
        self.max_statement.consistency_level = ConsistencyLevel.THREE

    def handle_cassandra_error(self, e):
        error_message = ""
        if isinstance(e, Unavailable):
            error_message = f"need {e.required_replicas} replicas, but only have {e.alive_replicas}"
        elif isinstance(e, NoHostAvailable):
            for _, err in e.errors.items():
                if isinstance(err, Unavailable):
                    error_message = f"need {err.required_replicas} replicas, but only have {err.alive_replicas}"
                    break
        else:
            error_message = str(e)
        return error_message


    def RecordTemps(self, request, context):
        try:
            # Create a record using the UDT structure
            record = StationRecord(tmin=request.tmin, tmax=request.tmax)
        
            # Execute the insert statement with the correct parameters
            self.session.execute(self.insert_statement, [request.station, request.date, record])
            return station_pb2.RecordTempsReply(error="")
        except Exception as e:
            # Handle errors appropriately
            error_message = self.handle_cassandra_error(e)
            return station_pb2.RecordTempsReply(error=error_message)
    
    def StationMax(self, request, context):
        try:
            result = self.session.execute(self.max_statement, [request.station])
            max_tmax = result.one()[0] if result else None
            return station_pb2.StationMaxReply(tmax=max_tmax, error="")
        except Exception as e:
            error_message = self.handle_cassandra_error(e)
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(error_message)
            return station_pb2.StationMaxReply(error=error_message)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    station_pb2_grpc.add_StationServicer_to_server(StationService(), server)
    server.add_insecure_port('[::]:5440')
    server.start()
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
