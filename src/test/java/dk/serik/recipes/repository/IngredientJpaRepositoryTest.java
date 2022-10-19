package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.IngredientEntity;
import lombok.extern.slf4j.Slf4j;

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
		List<IngredientEntity> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(8, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredientWater_WhenFetchingByName_ThenOk() {
		Optional<IngredientEntity> opIngredient = repository.findByName("Vand");
		Assertions.assertTrue(opIngredient.isPresent());
		Assertions.assertEquals("Almindelig postevand fra hanen", opIngredient.get().getDescription());
	}
	
	@Test
	public void givenExistingIngredientFlour_WhenFetchingByNameContains_ThenReturnTwo() {
		Optional<List<IngredientEntity>> opIngredients = repository.findAllByNameContains("mel");
		Assertions.assertTrue(opIngredients.isPresent());
		Assertions.assertEquals(2, opIngredients.get().size());
	}

	@Test
	public void giventExistingIngredientsKimAndSkaldele_WhenFetchingByDescriptionContains_TheReturnTwo() {
		Optional<List<IngredientEntity>> opIngredients = repository.findAllByDescriptionContains("skaldele og kim");
		Assertions.assertTrue(opIngredients.isPresent());
		Assertions.assertEquals(2, opIngredients.get().size());
	}
	
	@Test
	public void givenValidIngredient_WhenSaved_ThenOk() {		
		IngredientEntity saveAndFlush = repository.saveAndFlush(buildIngredientEntity());
		Assertions.assertEquals("Hakket oksekød", saveAndFlush.getName());
		Assertions.assertEquals("Majken", saveAndFlush.getCreatedBy());
		
		List<IngredientEntity> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(9, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredient_WhenDeleted_ThenOk() {
		Optional<IngredientEntity> opIngredient = repository.findById(UUID.fromString("e00aec55-2eb7-4eeb-a594-0bbb948f09c1"));
		Assertions.assertTrue(opIngredient.isPresent());
		repository.delete(opIngredient.get());
		opIngredient = repository.findById(UUID.fromString("e00aec55-2eb7-4eeb-a594-0bbb948f09c1"));
		Assertions.assertFalse(opIngredient.isPresent());
		
		List<IngredientEntity> ingredients = repository.findAll();
		Assertions.assertFalse(ingredients.isEmpty());
		Assertions.assertEquals(7, ingredients.size());
	}
	
	@Test
	public void givenExistingIngredient_WhenUpdateDescription_ThenOk() {
		Optional<IngredientEntity> opIngredient = repository.findById(UUID.fromString("9d2b8e95-e897-470a-b1dd-0cf03fb01465"));
		Assertions.assertTrue(opIngredient.isPresent());
		opIngredient.get().setDescription("Bare rugmel");
		IngredientEntity saveAndFlush = repository.saveAndFlush(opIngredient.get());
		Assertions.assertEquals("Majken", saveAndFlush.getUpdatedBy());
		Assertions.assertEquals("Bare rugmel", saveAndFlush.getDescription());
	}
	
	private IngredientEntity buildIngredientEntity() {
		IngredientEntity ingredient = new IngredientEntity();
		ingredient.setName("Hakket oksekød");
		ingredient.setDescription("Hakket oksekød fedt 4-7%");
		return ingredient;
	}
	
}
