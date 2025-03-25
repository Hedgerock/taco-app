package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.rabbit_mq.kitchen;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver implements OrderReceiver {
    //You can install delay in received method as second argument or add this prop to the properties or yml file
    private static final long DELAY_IN_MILLIS = 30_000;

    private static final String QUEUE_NAME = "tacocloud.order";

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    @Autowired //Annotation Autowired is not necessary if we have only one constructor
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public TacoOrder receiveOrder() {
        return this.rabbitTemplate.receiveAndConvert(QUEUE_NAME, new ParameterizedTypeReference<>() {});
    }

    //    @Override
//    public TacoOrder receiveOrder() {
//        Message message = this.rabbitTemplate.receive(QUEUE_NAME);
//
//        return message != null
//                ? (TacoOrder) this.messageConverter.fromMessage(message)
//                : null;
//    }
}
