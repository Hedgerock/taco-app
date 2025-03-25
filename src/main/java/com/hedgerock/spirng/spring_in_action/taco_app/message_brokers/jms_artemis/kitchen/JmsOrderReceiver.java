package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis.kitchen;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderReceiver implements OrderMessageReceiver {

    private final JmsTemplate jmsTemplate;
    private final MessageConverter converter;
    private final Destination destination;

    public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter converter, Destination destination) {
        this.jmsTemplate = jmsTemplate;
        this.converter = converter;
        this.destination = destination;
    }

    @Override
    public TacoOrder receiveOrder() throws JMSException {
        return (TacoOrder) this.jmsTemplate.receiveAndConvert();
    }

    //    @Override
//    public TacoOrder receiveOrder() throws JMSException {
//
//        Message message = this.jmsTemplate.receive(this.destination);
//
//        assert message != null;
//        return (TacoOrder) this.converter.fromMessage(message);
//    }
}
