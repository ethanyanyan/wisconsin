from kafka import KafkaConsumer, TopicPartition
import report_pb2
import os
import json
import sys
import threading
import calendar

broker = 'localhost:9092'
topic = 'temperatures'
partitions = [int(p) for p in sys.argv[1:]]  # Read partitions from command-line arguments

def load_or_initialize_partition_data(partition):
    path = f'/files/partition-{partition}.json'
    if os.path.exists(path):
        with open(path, 'r') as file:
            return json.load(file)
    else:
        return {"partition": partition, "offset": 0}

def update_stats(partition_data, record):
    report = record.value
    date = report.date
    year, month, _ = date.split('-')  # Extract year and month
    month_name = calendar.month_name[int(month)]
    
    if month_name not in partition_data:
        partition_data[month_name] = {}

    if year not in partition_data[month_name]:
        partition_data[month_name][year] = {"count": 1, "sum": report.degrees, "avg": report.degrees, "start": date, "end": date}

    month_year_data = partition_data[month_name][year]
    if date <= month_year_data["end"]:
        return  # Ignore duplicate or out-of-order message

    month_year_data["count"] += 1
    month_year_data["sum"] += report.degrees
    month_year_data["avg"] = month_year_data["sum"] / month_year_data["count"]
    month_year_data["end"] = date

def write_atomic_json(data, path):
    temp_path = path + ".tmp"
    with open(temp_path, "w") as f:
        json.dump(data, f, indent=4)
    os.rename(temp_path, path)

def process_partition(partition):
    consumer = KafkaConsumer(
        bootstrap_servers=[broker],
        group_id='stats_consumer',
        value_deserializer=lambda m: report_pb2.Report.FromString(m),
        enable_auto_commit=False
    )

    tp = TopicPartition(topic, partition)
    consumer.assign([tp])

    partition_data = load_or_initialize_partition_data(partition)

    consumer.seek(tp, partition_data["offset"])

    while True:
        messages = consumer.poll(timeout_ms=1000)
        for record in messages.get(tp, []):
            update_stats(partition_data, record)
            partition_data["offset"] = record.offset + 1
            write_atomic_json(partition_data, f'/files/partition-{partition}.json')

if __name__ == "__main__":
    threads = []
    for partition in partitions:
        thread = threading.Thread(target=process_partition, args=(partition,))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()  # Wait for all threads to finish
