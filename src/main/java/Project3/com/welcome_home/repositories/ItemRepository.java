package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
