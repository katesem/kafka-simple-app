package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaMessageProducer {

    public void sendMessages(String topicName, int numberOfMessages) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < numberOfMessages; i++) {
            String message = "Test Message " + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
            producer.send(record);
        }
        producer.flush();
        producer.close();
    }
}