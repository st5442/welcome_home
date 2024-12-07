package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
