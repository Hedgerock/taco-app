package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.kafka;

import com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.rabbit_mq.kitchen.KitchenUI;
import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder tacoOrder) {

        this.ui.displayOrder(tacoOrder);

    }

}
