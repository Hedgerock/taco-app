package com.hedgerock.spirng.spring_in_action.reactor.r2dbc.service;

import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data.TacoOrderRepository;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data.TacoRepository;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Taco;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.TacoOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TacoOrderAggregateService {

    private final TacoOrderRepository tacoOrderRepo;
    private final TacoRepository tacoRepo;

    public TacoOrderAggregateService(TacoOrderRepository tacoOrderRepo, TacoRepository tacoRepo) {
        this.tacoOrderRepo = tacoOrderRepo;
        this.tacoRepo = tacoRepo;
    }


    public Mono<TacoOrder> findOrderById(Long id) {
        return this.tacoOrderRepo
                .findById(id)
                .flatMap(order -> {
                    return this.tacoRepo
                            .findAllById(order.getTacoIds())
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            })
                            .last();
                }
        );
    }

    public Mono<TacoOrder> saveOrder(TacoOrder tacoOrder) {

        return Mono.just(tacoOrder)
                .flatMap(order -> {

                    List<Taco>tacos = order.getTacos();
                    order.setTacos(new ArrayList<>());

                    return this.tacoRepo.saveAll(tacos)
                        .map(taco -> {
                            order.addTaco(taco);
                            return order;
                        })
                    .last();
                })
                .flatMap(this.tacoOrderRepo::save);

    }

}
