package dk.serik.recipes;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

import dk.serik.recipes.repository.CategoryJpaRepository;
import dk.serik.recipes.service.CategoryService;
import dk.serik.recipes.service.CategoryServiceImpl;

@TestConfiguration
public class RecipesTestConfiguration {
	
	@Bean
	public CategoryService categoryService(CategoryJpaRepository categoryJpaRepository) {
		return new CategoryServiceImpl(categoryJpaRepository);
	}

}
