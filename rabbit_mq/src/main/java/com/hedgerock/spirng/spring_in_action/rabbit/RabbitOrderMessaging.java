package com.hedgerock.spirng.spring_in_action.rabbit;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class RabbitOrderMessaging implements RabbitOrder {

    private final RabbitTemplate template;

    public RabbitOrderMessaging(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {

        this.template.convertAndSend(tacoOrder, RabbitOrderMessaging::initProps);

    }

    private static Message initProps(Message message) {
        MessageProperties props = message.getMessageProperties();
        props.setHeader("X_ORDER_SOURCE", "WEB");

        return message;
    }

    //    @Override
//    public void sendOrder(TacoOrder tacoOrder) {
//        MessageConverter messageConverter = this.template.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//
//        Message message = messageConverter.toMessage(tacoOrder, props);
//
//        this.template.send("tacoclour.order", message);
//
//    }
}
