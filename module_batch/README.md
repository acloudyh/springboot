### zookeeper

> 启动以及停止
> - cd /Users/neo/zookeeper-3.4.13/bin
> - ./zkServer.sh start
> - ./zkServer.sh stop

### kafka
1. 启动以及停止

```
cd /Users/neo/kafka_2.11-2.0.0/bin
```
```
./kafka-server-start.sh ../config/server.properties
```


2. 查看topic

```
./kafka-topics.sh --list --zookeeper localhost:2181
```