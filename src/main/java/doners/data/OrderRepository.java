package doners.data;

import doners.DonerOrder;

public interface OrderRepository {
    DonerOrder save(DonerOrder order);
}
