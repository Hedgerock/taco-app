package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis.kitchen;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import jakarta.jms.JMSException;

public interface OrderMessageReceiver {

    TacoOrder receiveOrder() throws JMSException;

}
