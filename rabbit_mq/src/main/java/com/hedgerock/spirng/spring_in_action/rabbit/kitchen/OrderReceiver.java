package com.hedgerock.spirng.spring_in_action.rabbit.kitchen;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;

public interface OrderReceiver {

    TacoOrder receiveOrder();

}
