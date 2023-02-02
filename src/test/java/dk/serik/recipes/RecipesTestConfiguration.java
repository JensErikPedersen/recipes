package dk.serik.recipes;

import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.service.IngredientService;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

import dk.serik.recipes.repository.CategoryJpaRepository;
import dk.serik.recipes.service.CategoryService;
import dk.serik.recipes.service.CategoryServiceImpl;

@TestConfiguration
public class RecipesTestConfiguration {
	
	@Bean
	public CategoryService categoryService(CategoryJpaRepository categoryJpaRepository, CategoryMapper categoryMapper) {
		return new CategoryServiceImpl(categoryJpaRepository, categoryMapper);
	}

	@Bean
	public CategoryMapper categoryMapper() {
		return new CategoryMapper();
	}

}
