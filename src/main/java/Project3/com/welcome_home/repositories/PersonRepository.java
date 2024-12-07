package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity, ID Type>
public interface PersonRepository extends JpaRepository<Person, String> {
}
