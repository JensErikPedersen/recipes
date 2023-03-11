package dk.serik.recipes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = RecipesApplication.class)
@AutoConfigureTestDatabase
@Import({RecipesTestConfiguration.class, MapperTestConfiguration.class})
class RecipesApplicationTests {

	@Test
	void contextLoads() {
	}

}
