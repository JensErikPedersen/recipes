package dk.serik.recipes.repository;

import java.math.BigDecimal;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.CategoryEntity;
import dk.serik.recipes.entity.IngredientEntity;
import dk.serik.recipes.entity.RecipeEntity;
import dk.serik.recipes.entity.RecipeIngredientEntity;
import dk.serik.recipes.entity.RecipeIngredientPK;
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
		Optional<RecipeEntity> opRecipe = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");
		Assertions.assertTrue(opRecipe.isPresent());
		Optional<CategoryEntity> opCategory = categoryJpaRepository.findById("14d4c0b0-46ea-498d-a3a5-56060a3d7a7c");
		Assertions.assertTrue(opCategory.isPresent());
		opRecipe.get().setCategoryEntity(opCategory.get());
		RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
		recipeIngredientEntity.setAmount(new BigDecimal(1));
		recipeIngredientEntity.setUnitEntity(opUnit.get());
//		recipeIngredientEntity.setId(new RecipeIngredientKey(opRecipe.get(), opIngredient.get()));
		recipeIngredientEntity.setIngredientId(opIngredient.get());
		recipeIngredientEntity.setRecipeId(opRecipe.get());
		log.info("RecipeIngredientEntity to be persisted: {}", recipeIngredientEntity);
		RecipeIngredientEntity saveAndFlush = recipeIngredientJpaRepository.saveAndFlush(recipeIngredientEntity);
		
//		Optional<RecipeIngredientEntity> saveAndFlush = recipeIngredientJpaRepository.findByRecipeIdAndIngredientId(opRecipe.get().getId(), opIngredient.get().getId());
		log.info("Saved RecipeIngredient: {}", saveAndFlush);
		Assertions.assertEquals("5d22c394-b5ce-48c3-8199-72ccc92c737c", saveAndFlush.getRecipeId().getId());
	}
}
