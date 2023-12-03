import kafka.KafkaMessageConsumer;
import kafka.KafkaMessageProducer;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class KafkaTest {

    @Test
    public void testKafkaMessageProcessing() {
        String topicName = "test-topic";
        int numberOfMessages = 5;

        KafkaMessageProducer producer = new KafkaMessageProducer();
        producer.sendMessages(topicName, numberOfMessages);

        KafkaMessageConsumer consumer = new KafkaMessageConsumer();
        int messagesProcessed = consumer.receiveMessages(topicName, numberOfMessages);

        assertEquals(numberOfMessages, messagesProcessed);
    }
}