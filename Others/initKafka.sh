# wget https://downloads.apache.org/kafka/3.7.1/kafka_2.13-3.7.1.tgz
cd kafka/
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties

