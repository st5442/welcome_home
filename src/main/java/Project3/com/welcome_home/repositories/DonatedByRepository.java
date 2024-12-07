package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.DonatedBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonatedByRepository extends JpaRepository<DonatedBy, Integer> {
}
