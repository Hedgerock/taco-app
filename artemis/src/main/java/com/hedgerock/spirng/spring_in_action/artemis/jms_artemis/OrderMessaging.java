package com.hedgerock.spirng.spring_in_action.artemis.jms_artemis;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;

public interface OrderMessaging {

    void sendOrder(TacoOrder tacoOrder);

}
