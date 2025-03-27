package com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.client;

import com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.model.GratuityIn;
import com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.model.GratuityOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

@Configuration
@Slf4j
public class GratuityClientConfig {

    @Bean
    public ApplicationRunner gratuitySender(
            RSocketRequester.Builder builder
    ) {

        return args -> {

            RSocketRequester tcp = builder.tcp("localhost", 7000);

            Flux<GratuityIn> gratuityInFlux = Flux
                    .fromArray(new GratuityIn[]{
                        new GratuityIn(BigDecimal.valueOf(35.50), 18),
                        new GratuityIn(BigDecimal.valueOf(10.00), 15),
                        new GratuityIn(BigDecimal.valueOf(24.70), 19),
                        new GratuityIn(BigDecimal.valueOf(52.00), 21),
                        new GratuityIn(BigDecimal.valueOf(36.20), 32),
                        new GratuityIn(BigDecimal.valueOf(19.75), 44)
                    })
                    .delayElements(Duration.ofSeconds(1));

            tcp
                    .route("gratuity")
                    .data(gratuityInFlux)
                    .retrieveFlux(GratuityOut.class)
                    .subscribe(out -> log.info("{}% gratuity on {} is {}",
                            out.getPercent(), out.getBigTotal(), out.getGratuity())
                    );
        };



    }


}
