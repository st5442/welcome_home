package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Category;
import Project3.com.welcome_home.entities.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
