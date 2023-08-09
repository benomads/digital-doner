package doners.web.controller;

import doners.data.UserRepository;
import doners.entity.DonerOrder;
import doners.data.OrderRepository;
import doners.entity.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("donerOrder")
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }



    @GetMapping("/current")
    public String orderFrom() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid DonerOrder donerOrder,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        //@AuthenticationPrincipal  or User user = (User) authentication.getPrincipal(); or userRepository.findByUsername(principal.getName());
        donerOrder.setUser(user);

        log.info("Order submitted: {}", donerOrder);
        orderRepository.save(donerOrder);

        sessionStatus.setComplete();
        return "redirect:/design";
    }

}
