package doners;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "doner_order")
@Entity
public class DonerOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Delivery name is required")
    private String deliveryStreet;

    @NotBlank(message = "Delivery name is required")
    private String deliveryCity;

    @NotBlank(message = "Delivery name is required")
    private String deliveryState;

    @NotBlank(message = "Delivery name is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid Credit Card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\"|/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, message = "Invalid CVV", fraction = 0)
    @Column(name = "cc_cvv", nullable = false)
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Doner> doners = new ArrayList<>();

    public void addDoner(Doner doner) {
        this.doners.add(doner);
    }


}
