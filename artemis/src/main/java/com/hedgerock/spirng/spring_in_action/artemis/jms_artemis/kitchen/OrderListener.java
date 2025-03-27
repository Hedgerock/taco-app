package com.hedgerock.spirng.spring_in_action.artemis.jms_artemis.kitchen;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocoloud.order.queue")
    public void receiveOrder(TacoOrder tacoOrder) {

        this.ui.displayOrder(tacoOrder);

    }

}
