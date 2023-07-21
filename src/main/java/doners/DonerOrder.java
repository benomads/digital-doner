package doners;

import java.util.ArrayList;
import java.util.List;

public class DonerOrder {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Doner> doners = new ArrayList<>();

    public void addDoner(Doner doner) {
        this.doners.add(doner);
    }
}
