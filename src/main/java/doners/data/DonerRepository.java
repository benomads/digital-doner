package doners.data;

import doners.entity.Doner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonerRepository extends JpaRepository<Doner, Long> {

}
