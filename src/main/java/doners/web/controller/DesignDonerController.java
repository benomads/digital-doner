package doners.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import doners.entity.DonerOrder;
import doners.data.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import doners.entity.Ingredient;
import doners.entity.Ingredient.Type;
import doners.entity.Doner;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("donerOrder")
public class DesignDonerController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignDonerController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType((List<Ingredient>) ingredients, type));
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
