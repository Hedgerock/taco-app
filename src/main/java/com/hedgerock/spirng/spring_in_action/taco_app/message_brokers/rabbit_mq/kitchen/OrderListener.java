package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.rabbit_mq.kitchen;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder tacoOrder) {

        this.ui.displayOrder(tacoOrder);

    }
}

