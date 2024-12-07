package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.entities.ActId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepository extends JpaRepository<Act, ActId> {
}
