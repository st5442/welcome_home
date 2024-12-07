package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.ItemIn;
import Project3.com.welcome_home.entities.ItemInId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInRepository extends JpaRepository<ItemIn, ItemInId> {
}
