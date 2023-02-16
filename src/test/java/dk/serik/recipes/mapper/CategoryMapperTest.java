package dk.serik.recipes.mapper;

import dk.serik.recipes.RecipesTestConfiguration;
import dk.serik.recipes.model.Category;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(RecipesTestConfiguration.class)
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper mapper;


    private Category mockCategory() {
        Category mock = new Category();
        mock.setName("Brød");
        mock.setDescription("Brød til alle måltider");
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        return mock;
    }
}
