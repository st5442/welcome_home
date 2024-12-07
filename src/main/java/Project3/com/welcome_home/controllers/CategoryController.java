package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Category;
import Project3.com.welcome_home.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{mainCategory}/{subCategory}")
    public Category getCategory(@PathVariable String mainCategory, @PathVariable String subCategory) {
        return categoryService.getCategory(mainCategory, subCategory);
    }
}
