package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
    @Query(value = "SELECT * FROM ordered where orderID = ?", nativeQuery = true)
    Optional<Ordered> findByOrderID(int orderID);
}
