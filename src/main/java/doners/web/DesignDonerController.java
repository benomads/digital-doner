package doners.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import doners.DonerOrder;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import doners.Ingredient;
import doners.Ingredient.Type;
import doners.Doner;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("donerOrder")
public class DesignDonerController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("BEME", "Beef Meat", Type.PROTEIN),
                new Ingredient("CHFI", "Chicken Fillet", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("CUBR", "Cucumbers", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("KTUP", "Ketchup", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "donerOrder")
    public DonerOrder order() {
        return new DonerOrder();
    }
    @ModelAttribute(name = "doner")
    public Doner doner() {
        return new Doner();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping("")
    public String processDoner(@Valid Doner doner,
                               Errors errors,
                               @ModelAttribute DonerOrder donerOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        donerOrder.addDoner(doner);
        log.info("Processing doner: {}", doner);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
