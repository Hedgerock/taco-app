package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.kafka;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessaging {

    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {

        this.kafkaTemplate.sendDefault(tacoOrder);

    }
}
