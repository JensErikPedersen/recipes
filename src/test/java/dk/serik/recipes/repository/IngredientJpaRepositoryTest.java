package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class IngredientJpaRepositoryTest {
	
	@Autowired
	private IngredientJpaRepository repository;
	
	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Majken");
	}
	
	@Test
	public void givenExistingIngredients_WhenFetchingAll_ThenReturnEight() {
		List<Ingredient> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(9, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredientWater_WhenFetchingByName_ThenOk() {
		Optional<Ingredient> opIngredient = repository.findByName("Vand");
		Assertions.assertTrue(opIngredient.isPresent());
		Assertions.assertEquals("Almindelig postevand fra hanen", opIngredient.get().getDescription());
	}
	
	@Test
	public void givenExistingIngredientFlour_WhenFetchingByNameContains_ThenReturnTwo() {
		Optional<List<Ingredient>> opIngredients = repository.findAllByNameContains("mel");
		Assertions.assertTrue(opIngredients.isPresent());
		Assertions.assertEquals(3, opIngredients.get().size());
	}

	@Test
	public void giventExistingIngredientsKimAndSkaldele_WhenFetchingByDescriptionContains_TheReturnTwo() {
		Optional<List<Ingredient>> opIngredients = repository.findAllByDescriptionContains("skaldele og kim");
		Assertions.assertTrue(opIngredients.isPresent());
		Assertions.assertEquals(3, opIngredients.get().size());
	}
	
	@Test
	public void givenValidIngredient_WhenSaved_ThenOk() {		
		OffsetDateTime now = OffsetDateTime.now();
		Ingredient saveAndFlush = repository.saveAndFlush(buildIngredientEntity());
		Assertions.assertEquals("Hakket oksek??d", saveAndFlush.getName());
		assertThat(now).isCloseTo(saveAndFlush.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Majken").isEqualTo(saveAndFlush.getCreatedBy());
		List<Ingredient> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(10, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredient_WhenDeleted_ThenOk() {
		Optional<Ingredient> opIngredient = repository.findById("e00aec55-2eb7-4eeb-a594-0bbb948f09c1");
		Assertions.assertTrue(opIngredient.isPresent());
		repository.delete(opIngredient.get());
		opIngredient = repository.findById("e00aec55-2eb7-4eeb-a594-0bbb948f09c1");
		Assertions.assertFalse(opIngredient.isPresent());
		
		List<Ingredient> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(8, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredient_WhenUpdateDescription_ThenOk() {
		Optional<Ingredient> opIngredient = repository.findById("9d2b8e95-e897-470a-b1dd-0cf03fb01465");
		Assertions.assertTrue(opIngredient.isPresent());
		opIngredient.get().setDescription("Bare rugmel");
		Ingredient saveAndFlush = repository.saveAndFlush(opIngredient.get());
		Assertions.assertEquals("Majken", saveAndFlush.getUpdatedBy());
		Assertions.assertEquals("Bare rugmel", saveAndFlush.getDescription());
	}
	
	private Ingredient buildIngredientEntity() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Hakket oksek??d");
		ingredient.setDescription("Hakket oksek??d fedt 4-7%");
		return ingredient;
	}
	
}
