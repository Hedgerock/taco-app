package com.hedgerock.spirng.spring_in_action.kafka;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
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
