package doners;

import doners.data.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalDonerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalDonerApplication.class, args);
    }

}
