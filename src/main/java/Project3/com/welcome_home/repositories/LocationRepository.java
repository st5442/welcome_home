package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.entities.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
