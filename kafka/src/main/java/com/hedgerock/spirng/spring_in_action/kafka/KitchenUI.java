package com.hedgerock.spirng.spring_in_action.kafka;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(TacoOrder tacoOrder) {
        log.info("RECEIVED ORDER: {}", tacoOrder);
    }

}
