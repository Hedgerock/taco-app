package com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_stream.client;

import com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_stream.model.StockQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class StockQuoteConfig {

    @Bean
    public ApplicationRunner requestStreamSender(
            RSocketRequester.Builder requestBuilder
    ) {

        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);
            String symbol = "Cryptocurrency";

            tcp
                    .route("stock/{symbol}", symbol)
                    .data("")
                    .retrieveFlux(StockQuote.class)
                    .doOnNext(stockQuote -> {
                        log.info("Price of {} is {} (at {})",
                                stockQuote.getSymbol(),
                                stockQuote.getPrice(),
                                stockQuote.getTimestamp()
                        );
                    })
                    .subscribe();
        };

    }

}
