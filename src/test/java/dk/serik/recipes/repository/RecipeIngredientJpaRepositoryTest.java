package dk.serik.recipes.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.CategoryEntity;
import dk.serik.recipes.entity.IngredientEntity;
import dk.serik.recipes.entity.RecipeEntity;
import dk.serik.recipes.entity.RecipeIngredientEntity;
import dk.serik.recipes.entity.UnitEntity;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class RecipeIngredientJpaRepositoryTest {
	
	@Autowired
	private RecipeIngredientJpaRepository recipeIngredientJpaRepository;
	
	@Autowired
	private IngredientJpaRepository ingredientJpaRepository;
	
	@Autowired
	private UnitJpaRepository unitJpaRepository;
	
	@Autowired
	private RecipeJpaRepository recipeJpaRepository;
	
	@Autowired
	private CategoryJpaRepository categoryJpaRepository;

	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Jens");
	}
	
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenRecipeAndUnit_WhenIngredientIsAdded_ThenOk() {
		Optional<UnitEntity> opUnit = unitJpaRepository.findById("b9cef3df-4bb5-49ab-8bde-5848d1363bce");  // deciliter
		Assertions.assertTrue(opUnit.isPresent());
		Optional<IngredientEntity> opIngredient = ingredientJpaRepository.findById("381e5cd5-0a5d-48d2-b69c-71516254937e");  // surdej
		Assertions.assertTrue(opIngredient.isPresent());
		Optional<RecipeEntity> opRecipe = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");  // Fuldkorns hvedebrød
		Assertions.assertTrue(opRecipe.isPresent());
		Optional<CategoryEntity> opCategory = categoryJpaRepository.findById("14d4c0b0-46ea-498d-a3a5-56060a3d7a7c");  // Brød
		Assertions.assertTrue(opCategory.isPresent());
		opRecipe.get().setCategoryEntity(opCategory.get());
		RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
		recipeIngredientEntity.setAmount(new BigDecimal(1));
		recipeIngredientEntity.setUnitEntity(opUnit.get());
		recipeIngredientEntity.setIngredientEntity(opIngredient.get());
		recipeIngredientEntity.setRecipeEntity(opRecipe.get());
		log.info("RecipeIngredientEntity to be persisted: {}", recipeIngredientEntity);
		OffsetDateTime now = OffsetDateTime.now();
		RecipeIngredientEntity saveAndFlush = recipeIngredientJpaRepository.saveAndFlush(recipeIngredientEntity);
		log.info("Saved RecipeIngredient: {}", saveAndFlush);
		Assertions.assertEquals("5d22c394-b5ce-48c3-8199-72ccc92c737c", saveAndFlush.getRecipeEntity().getId());
		assertThat(now).isCloseTo(saveAndFlush.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(saveAndFlush.getCreatedBy());
	}
	

	@Test	
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})	
	public void givenRecipeIngredients_WhenFetchingRecipe_ThenAllIngredientsFetched() {
		Optional<List<RecipeIngredientEntity>> allIngrOp = recipeIngredientJpaRepository.findAllByRecipeEntityId("5d22c394-b5ce-48c3-8199-72ccc92c737c"); // Fuldkorns hvedebrød
		Assertions.assertTrue(allIngrOp.isPresent());
		Assertions.assertEquals(6, allIngrOp.get().size());
	}
	
	@Test	
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})	
	public void givenRecipeIngredients_WhenFetchingRecipeIngredients_ThenRecipeIngredientIsFetched() {
		Optional<RecipeIngredientEntity> recIngrOp = recipeIngredientJpaRepository.findByRecipeEntityIdAndIngredientEntityId("ce07075c-38b4-4b52-831c-5a9ce105e4af", "713ff039-25f2-471f-a1a4-ab8fc9efc8b0"); // Hvedebrød med Rugmel -> Gær
		Assertions.assertTrue(recIngrOp.isPresent());
		Assertions.assertEquals("Hvedebrød med Rugmel", recIngrOp.get().getRecipeEntity().getName());
		Assertions.assertEquals("Gær", recIngrOp.get().getIngredientEntity().getName());
	}
	
	@Test	
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})	
	public void givenRecipeIngredients_WhenFetchingIngredients_ThenAllIngredientsFetched() {
		Optional<List<RecipeIngredientEntity>> recIngrOp = recipeIngredientJpaRepository.findAllByIngredientEntityId("549ab6e6-f2d8-4ab3-8ba8-6bc7af82f2fb"); // Hvedemel
		Assertions.assertTrue(recIngrOp.isPresent());
		Assertions.assertEquals(2, recIngrOp.get().size());
	}
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenRecipeWithIngredients_WhenIngredientIsDeleted_ThenNoOtherEntitiesIsDeleted() {
		Optional<RecipeIngredientEntity> recIngrOp = recipeIngredientJpaRepository.findByRecipeEntityIdAndIngredientEntityId("ce07075c-38b4-4b52-831c-5a9ce105e4af", "e0aa2252-c5f1-4c87-b42c-9dd10486f366"); // Hvedebrød med Rugmel -> Salt
		Assertions.assertTrue(recIngrOp.isPresent());
		String unitId = recIngrOp.get().getUnitEntity().getId();
		recipeIngredientJpaRepository.delete(recIngrOp.get());
		Optional<List<RecipeIngredientEntity>> allIngrOp = recipeIngredientJpaRepository.findAllByRecipeEntityId("ce07075c-38b4-4b52-831c-5a9ce105e4af"); // Fuldkorns hvedebrød
		Assertions.assertTrue(allIngrOp.isPresent());
		Assertions.assertEquals(5, allIngrOp.get().size());
		
		// check all related entities exist
		Optional<IngredientEntity> ingrOp = ingredientJpaRepository.findById("e0aa2252-c5f1-4c87-b42c-9dd10486f366"); // Salt
		Assertions.assertTrue(ingrOp.isPresent());
		
		Optional<UnitEntity> unitOp = unitJpaRepository.findById(unitId);
		Assertions.assertTrue(unitOp.isPresent());
	}
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenRecipeWithIngredients_WhenAmountIsUpdated_ThenOk() {
		Optional<RecipeIngredientEntity> recIngrOp = recipeIngredientJpaRepository.findByRecipeEntityIdAndIngredientEntityId("ce07075c-38b4-4b52-831c-5a9ce105e4af", "e0aa2252-c5f1-4c87-b42c-9dd10486f366"); // Hvedebrød med Rugmel -> Salt
		Assertions.assertTrue(recIngrOp.isPresent());
		Assertions.assertEquals(new BigDecimal(16).intValue(), recIngrOp.get().getAmount().intValue());
		Assertions.assertEquals("Majken", recIngrOp.get().getUpdatedBy());
		recIngrOp.get().setAmount(new BigDecimal(15));
		OffsetDateTime now = OffsetDateTime.now();
		RecipeIngredientEntity saveAndFlush = recipeIngredientJpaRepository.saveAndFlush(recIngrOp.get());
		Assertions.assertEquals(new BigDecimal(15).intValue(), saveAndFlush.getAmount().intValue());
		Assertions.assertEquals("Jens", saveAndFlush.getUpdatedBy());	
		assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));		
	}
}
