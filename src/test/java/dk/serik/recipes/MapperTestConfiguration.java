package dk.serik.recipes;

import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.mapper.CategoryMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MapperTestConfiguration {

    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapperImpl();
    }
}
