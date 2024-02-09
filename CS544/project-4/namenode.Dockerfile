FROM p4-hdfs
RUN hdfs namenode -format
CMD ["hdfs", "namenode", "-D", "dfs.namenode.stale.datanode.interval=10000", "-D", "dfs.namenode.heartbeat.recheck-interval=30000", "-fs", "hdfs://boss:9000"]