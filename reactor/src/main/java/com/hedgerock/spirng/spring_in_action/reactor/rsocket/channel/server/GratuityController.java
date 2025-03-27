package com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.server;

import com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.model.GratuityIn;
import com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.model.GratuityOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Controller
@Slf4j
public class GratuityController {

    @MessageMapping("gratuity")
    public Flux<GratuityOut> calculate(
            Flux<GratuityIn> gratuityInFlux
    ) {

        return gratuityInFlux
                .doOnNext(GratuityController::loggingProcess)
                .map(GratuityController::initGratuityOut);

    }

    private static void loggingProcess(GratuityIn in) {
        log.info("Calculation gratuity: {}", in);
    }

    private static GratuityOut initGratuityOut(GratuityIn in) {
        double percent = in.getPercent() / 100.0;
        BigDecimal gratuity = in.getBillTotal()
                .multiply(BigDecimal.valueOf(percent));

        return new GratuityOut(in.getBillTotal(), in.getPercent(), gratuity);
    }
}
