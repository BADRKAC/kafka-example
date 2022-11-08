package com.proof;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
class KafkaApplicationTest {

  @Autowired
  public KafkaTemplate<String, String> template;
  @Autowired
  public KafkaConsumer consumer;
  @Autowired
  public KafkaProducer producer;

  @Value("${test.topic}")
  private String topic;


  @Test
   void givenEmbeddedKafkaBroker_whenSendingWithDefaultTemplate_thenMessageReceived()
    throws Exception {
    String data = "Sending with default template";

    template.send(topic, data);

    assertThat(consumer.getPayload(), containsString(data));
  }

  @Test
    void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
    throws Exception {
    String data = "Sending with our own simple KafkaProducer";

    producer.send(topic, data);

    assertThat(consumer.getPayload(), containsString(data));
  }

}