package com.hedgerock.spirng.spring_in_action.artemis.jms_artemis.kitchen;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import jakarta.jms.JMSException;

public interface OrderMessageReceiver {

    TacoOrder receiveOrder() throws JMSException;

}
