from kafka import KafkaConsumer
import report_pb2
import threading

broker = 'localhost:9092'

def kafka_consumer_thread():
    consumer = KafkaConsumer(
        'temperatures',
        bootstrap_servers=[broker],
        group_id='debug',
        value_deserializer=lambda m: report_pb2.Report().FromString(m)  # Correctly deserialize the protobuf message
    )


    consumer.subscribe(['temperatures'])
    consumer.poll(1000)
    
    for message in consumer:

        report = message.value

        print_dict = {
            'partition': message.partition,
            'key': message.key.decode('utf-8') if message.key else None,
            'date': report.date,
            'degrees': report.degrees
        }

        print(print_dict)

# Create and start the consumer thread
consumer_thread = threading.Thread(target=kafka_consumer_thread)
consumer_thread.start()

consumer_thread.join()
