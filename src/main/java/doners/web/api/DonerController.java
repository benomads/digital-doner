package doners.web.api;

import doners.data.DonerRepository;
import doners.data.OrderRepository;
import doners.entity.Doner;
import doners.entity.DonerOrder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/doners",
                produces = "application/json")
@CrossOrigin(origins = "http://digital-doner:8080")
public class DonerController {
    private final DonerRepository donerRepository;
    private final OrderRepository orderRepository;

    public DonerController(DonerRepository donerRepository, OrderRepository orderRepository) {
        this.donerRepository = donerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Doner> recentDoner() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return donerRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doner> donerById(@PathVariable("id") Long id) {
        Optional<Doner> optionalDoner = donerRepository.findById(id);
        if (optionalDoner.isPresent()) {
            return new ResponseEntity<>(optionalDoner.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Doner postDoner(@RequestBody Doner doner) {
        return donerRepository.save(doner);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public DonerOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody DonerOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try{
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
