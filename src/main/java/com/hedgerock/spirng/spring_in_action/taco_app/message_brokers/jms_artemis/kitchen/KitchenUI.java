package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis.kitchen;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(TacoOrder tacoOrder) {

        log.info("RECEIVED ORDER: {}", tacoOrder);

    }

}
