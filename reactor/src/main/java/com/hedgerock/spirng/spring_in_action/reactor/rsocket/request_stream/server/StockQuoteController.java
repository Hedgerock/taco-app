package com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_stream.server;


import com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_stream.model.StockQuote;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Controller
public class StockQuoteController {

    @MessageMapping("stock/{symbol}")
    public Flux<StockQuote> getStockPrice(
            @DestinationVariable String symbol
    ) {

        return Flux
                .interval(Duration.ofSeconds(1))
                .map(i -> {
                    BigDecimal price = BigDecimal.valueOf(Math.random() * 10);
                    return new StockQuote(symbol, price, Instant.now());
                });

    }

}