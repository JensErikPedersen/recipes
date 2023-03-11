package dk.serik.recipes.service;

import dk.serik.recipes.RecipesTestConfiguration;
import dk.serik.recipes.repository.IngredientJpaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(RecipesTestConfiguration.class)
@Disabled
public class IngredientServiceTest {
	
	@Autowired
	private IngredientService ingredientService;
	
	@MockBean
	private IngredientJpaRepository ingredientJpaRepository;
	
	@Test
	@DisplayName("Check that Context is OK")
	public void contextIsOk() {
		assertThat(ingredientService).isNotNull();
		assertThat(ingredientJpaRepository).isNotNull();		
	}
	
	

}
