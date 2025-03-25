package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService  implements OrderMessaging {

    private final JmsTemplate template;
    private final Destination destination;

    public JmsOrderMessagingService(JmsTemplate template, Destination destination) {
        this.template = template;
        this.destination = destination;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
        this.template.convertAndSend(
                this.destination,
                tacoOrder,
                JmsOrderMessagingService::addOrderSource
        );
    }

    private static Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

    //    @Override
//    public void sendOrder(TacoOrder tacoOrder) {
//
//        this.template.send(session -> {
//
//            Message message = session.createObjectMessage(tacoOrder);
//            message.setStringProperty("X_ORDER_SOURCE", "WEB");
//            return message;
//
//        });
//
//    }


}
