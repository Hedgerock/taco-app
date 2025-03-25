package com.hedgerock.spirng.spring_in_action.taco_app.controllers.api;

import com.hedgerock.spirng.spring_in_action.taco_app.data.rest_repositories.TacoOrderRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis.JmsOrderMessagingService;
import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoOrderRestController {

    private final TacoOrderRepository repository;
    private final JmsOrderMessagingService messagingService;

    public TacoOrderRestController(TacoOrderRepository repository, JmsOrderMessagingService messagingService) {
        this.repository = repository;
        this.messagingService = messagingService;
    }

    @GetMapping(produces = "application/json")
    public Iterable<TacoOrder> getAllOrders() {
        return this.repository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(
            @RequestBody TacoOrder tacoOrder
    ) {
        this.messagingService.sendOrder(tacoOrder);
        return this.repository.save(tacoOrder);
    }

    @PutMapping(value = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(
            @PathVariable(name="orderId") Long orderId,
            @RequestBody TacoOrder tacoOrder
    ) {
        tacoOrder.setId(orderId);
        return this.repository.save(tacoOrder);
    }

    @PatchMapping(value = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(
            @PathVariable(name="orderId") Long orderId,
            @RequestBody TacoOrder patch
    ) {
        TacoOrder curOrder= this.repository.findById(orderId).orElseThrow(RuntimeException::new);
        curOrder.updateTacoOrder(patch);

        return this.repository.save(curOrder);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(
            @PathVariable(name = "orderId") Long orderId
    ) {
        this.repository.deleteById((orderId));
    }
}
