package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers.api;

import com.hedgerock.spirng.spring_in_action.view_taco_app.data.rest_repositories.TacoCrudRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.data.rest_repositories.TacoRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoRestController {

    private final TacoRepository tacoRepository;
    private final TacoCrudRepository tacoCrudRepository;

    public TacoRestController(TacoRepository tacoRepository, TacoCrudRepository tacoCrudRepository) {
        this.tacoRepository = tacoRepository;
        this.tacoCrudRepository = tacoCrudRepository;
    }


    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {

        PageRequest page = PageRequest.of(0, 20, Sort.by("createdAt").descending());

        return tacoRepository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(
            @PathVariable("id") Long id
    ) {
        Optional<Taco> taco = this.tacoCrudRepository.findById(id);

        return taco.isPresent()
                ? new ResponseEntity<>(taco.get(), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
