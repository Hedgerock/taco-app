package com.hedgerock.spirng.spring_in_action.reactor.reactiveController;

import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data.TacoRepository;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Taco;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoRestController {

    private final TacoRepository tacoRepository;

    public TacoRestController(
            TacoRepository tacoRepository
    ) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Flux<Taco> recentTacos() {
        return this.tacoRepository.findAll().take(12);
    }

    @GetMapping("/{id}")
    public Mono<Taco> getTacoById(
            @PathVariable("id") Long id
    ) {
        return this.tacoRepository.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(
            @RequestBody Mono<Taco> taco
    ) {
        return taco.flatMap(this.tacoRepository::save);
    }

}