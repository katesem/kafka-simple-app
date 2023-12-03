package kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaMessageConsumer {

    public int receiveMessages(String topicName, int expectedMessages) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //set up the consumer group name
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "first-group");

        //set up deserializer from bytes to string type for key and value
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicName));  //subscribing to the kafka topic

        int messagesProcessed = 0;
        ConsumerRecords<String, String> records;
        do {
            records = consumer.poll(Duration.ofSeconds(1));
            messagesProcessed += records.count();
        } while (messagesProcessed < expectedMessages);

        consumer.close();
        return messagesProcessed;
    }
}