package doners.data;

import doners.entity.DonerOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<DonerOrder, Long> {
    List<DonerOrder> findByDeliveryZip(String deliveryZip);
}
