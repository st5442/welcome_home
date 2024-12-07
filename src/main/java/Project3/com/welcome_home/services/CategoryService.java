package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Category;
import Project3.com.welcome_home.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategory(String mainCategory, String subCategory) {
        return categoryRepository.findById(new CategoryId(mainCategory, subCategory)).orElse(null);
    }
}
