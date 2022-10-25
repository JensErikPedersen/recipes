package dk.serik.recipes.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
import dk.serik.recipes.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class CategoryJpaRepositoryTest {

	@Autowired
	private CategoryJpaRepository categoryRepository;
	
	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Jens");
	}
	
	@Test
	public void givenCategoryBreadIsPresent_WhenFetchingCategoryBread_ThenOk() {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findByName("Brød");
		Assertions.assertTrue(categoryEntity.isPresent());
		Assertions.assertEquals("Jens", categoryEntity.get().getCreatedBy());
	}
	
	@Test
	public void givenCategoryKagerIsPresent_WhenFetchingCategoryKager_ThenOk() {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findByName("Kager");
		Assertions.assertTrue(categoryEntity.isPresent());
		Assertions.assertEquals("Majken", categoryEntity.get().getCreatedBy());
	}
	
	@Test
	public void givenCategoryEntityWienerBread_WhenSavingCategory_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		CategoryEntity categoryEntity = categoryRepository.saveAndFlush(buildWienerBreadEntity());		
		assertThat(now).isCloseTo(categoryEntity.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(categoryEntity.getCreatedBy());
		Assertions.assertEquals("Wienerbrød", categoryEntity.getName());		
	}
	
	@Test
	public void givenThreeCategories_WhenFetchingAll_ThenFourInList() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		Assertions.assertFalse(categoryEntities.isEmpty());
		Assertions.assertEquals(4, categoryEntities.size());
	}
	
	@Test
	public void givenExistingCategory_WhenNameIsChanged_ThenOk() {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findByName("Brød");
		Assertions.assertTrue(categoryEntity.isPresent());
		categoryEntity.get().setName("Bread");
		OffsetDateTime now = OffsetDateTime.now();
		CategoryEntity savedCategory = categoryRepository.saveAndFlush(categoryEntity.get());
		Assertions.assertEquals("Bread", savedCategory.getName());
		assertThat(now).isCloseTo(savedCategory.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(savedCategory.getUpdatedBy());
	}
	
	@Test
	public void givenExistingCategories_WhenLookupByName_er_ThenFindTwo() {
		Optional<List<CategoryEntity>> opCategories = categoryRepository.findAllByNameContains("er");
		Assertions.assertTrue(opCategories.isPresent());
		Assertions.assertEquals(2, opCategories.get().size());
	}
	
	private CategoryEntity buildWienerBreadEntity() {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName("Wienerbrød");
		return categoryEntity;
	}
}
