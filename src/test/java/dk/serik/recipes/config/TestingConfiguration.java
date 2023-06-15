package dk.serik.recipes.config;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.repository.CategoryJpaRepository;
import dk.serik.recipes.service.CategoryService;
import dk.serik.recipes.service.CategoryServiceImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.boot.test.context.TestConfiguration
public class TestingConfiguration {

    @Bean
    public CategoryService categoryService(CategoryJpaRepository categoryJpaRepository, Session session) {
        return new CategoryServiceImpl(categoryJpaRepository, session);
    }
}
