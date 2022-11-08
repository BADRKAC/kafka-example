package com.proof;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
  private String payload;
  @KafkaListener(topics = "${dev.topic}",groupId = "${spring.kafka.consumer.group-id}")
   public void listener(String data){
    //System.out.println("Listener received data: "+ data);
    payload = data;

  }
  public String getPayload() {
    return payload;
  }
}

