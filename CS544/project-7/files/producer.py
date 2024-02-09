from kafka import KafkaAdminClient
from kafka.admin import NewTopic
from kafka.errors import UnknownTopicOrPartitionError, TopicAlreadyExistsError
import time

broker = 'localhost:9092'
admin_client = KafkaAdminClient(bootstrap_servers=[broker])

try:
    admin_client.delete_topics(["temperatures"])
    print("Deleted topics successfully")
except UnknownTopicOrPartitionError:
    print("Cannot delete topic/s (may not exist yet)")

time.sleep(3) # Deletion sometimes takes a while to reflect

# TODO: Create topic 'temperatures' with 4 partitions and replication factor = 1

try:
    admin_client.create_topics([NewTopic("temperatures", 4, 1)])
except TopicAlreadyExistsError:
    pass

print("Topics:", admin_client.list_topics())


import report_pb2
from kafka import KafkaProducer
from datetime import datetime
import calendar
import weather
import threading

def get_month_name(date_str):
    date_obj = datetime.strptime(date_str, '%Y-%m-%d')
    return calendar.month_name[date_obj.month]

def producer_thread():
    producer = KafkaProducer(
        bootstrap_servers=['localhost:9092'],
        retries=10,
        acks='all',  # Wait for all in-sync replicas to ack the message
        value_serializer=lambda v: v  # Serialize using protobuf
    )

    for date, degrees in weather.get_next_weather(delay_sec=0.1):
        report = report_pb2.Report()
        report.date = date
        report.degrees = degrees

        report_bytes = report.SerializeToString()
        month_key = get_month_name(date).encode('utf-8')

        producer.send('temperatures', key=month_key, value=report_bytes)


producer_thread_instance = threading.Thread(target=producer_thread)
producer_thread_instance.start()
