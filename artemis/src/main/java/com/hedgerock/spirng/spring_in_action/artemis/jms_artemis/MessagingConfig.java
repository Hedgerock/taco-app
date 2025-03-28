package com.hedgerock.spirng.spring_in_action.artemis.jms_artemis;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public Destination orderQueue() {

        return new ActiveMQQueue("tacocloud.order.queue");

    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        var messageConverter = new MappingJackson2MessageConverter();

        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMapping = new HashMap<>();
        typeIdMapping.put("order", TacoOrder.class);

        messageConverter.setTypeIdMappings(typeIdMapping);

        return new MappingJackson2MessageConverter();
    }

}
