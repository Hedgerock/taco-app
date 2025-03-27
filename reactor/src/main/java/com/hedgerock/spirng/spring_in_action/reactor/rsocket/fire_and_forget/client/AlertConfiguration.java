package com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.client;


import com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.model.Alert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.time.Instant;

import static com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.model.Alert.Level.RED;

@Slf4j
@Configuration
public class AlertConfiguration {

    @Bean
    public ApplicationRunner alertSender(
            RSocketRequester.Builder requestBuilder
    ) {

        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);

            tcp
                    .route("alert")
                    .data(new Alert(RED, "Craig", Instant.now()))
                    .send()
                    .subscribe();

            log.info("Alert sent");
        };

    }

}
