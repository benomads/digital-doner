package doners.data;

import doners.DonerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<DonerOrder, Long> {

}
