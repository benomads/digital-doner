package doners;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IngredientRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Маппинг поля ingredient на связь с сущностью Ingredient
    @ManyToOne
    @JoinColumn(name = "ingredient") // Имя столбца для внешнего ключа
    private Ingredient ingredient;

    // Добавьте конструкторы, геттеры, сеттеры и другие методы по необходимости

}
