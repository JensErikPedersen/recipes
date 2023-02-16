package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

//@ExtendWith(SpringExtension.class)
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
	
	@ParameterizedTest
	@CsvSource(value={"Brød:Jens", "Kager:Majken"}, delimiter = ':')
	public void givenCategoryIsPresent_WhenFetchingCategory_ThenCreatedByOk(String category, String createdBy) {
		Optional<Category> categoryEntity = categoryRepository.findByName(category);
		Assertions.assertTrue(categoryEntity.isPresent());
		Assertions.assertEquals(createdBy, categoryEntity.get().getCreatedBy());
	}
	
	
	@Test
	public void givenCategoryWienerBread_WhenSavingCategory_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		Category category = categoryRepository.saveAndFlush(buildWienerBreadEntity());
		assertThat(now).isCloseTo(category.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(category.getCreatedBy());
		Assertions.assertEquals("Wienerbrød", category.getName());
	}
	
	@Test
	public void givenThreeCategories_WhenFetchingAll_ThenFourInList() {
		List<Category> categoryEntities = categoryRepository.findAll();
		Assertions.assertFalse(categoryEntities.isEmpty());
		Assertions.assertEquals(4, categoryEntities.size());
	}
	
	@Test
	public void givenExistingCategory_WhenNameIsChanged_ThenOk() {
		Optional<Category> categoryEntity = categoryRepository.findByName("Brød");
		Assertions.assertTrue(categoryEntity.isPresent());
		categoryEntity.get().setName("Bread");
		OffsetDateTime now = OffsetDateTime.now();
		Category savedCategory = categoryRepository.saveAndFlush(categoryEntity.get());
		Assertions.assertEquals("Bread", savedCategory.getName());
		assertThat(now).isCloseTo(savedCategory.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(savedCategory.getUpdatedBy());
	}
	
	@Test
	public void givenExistingCategories_WhenLookupByName_er_ThenFindTwo() {
		Optional<List<Category>> opCategories = categoryRepository.findAllByNameContains("er");
		Assertions.assertTrue(opCategories.isPresent());
		Assertions.assertEquals(2, opCategories.get().size());
	}
	
	private Category buildWienerBreadEntity() {
		Category category = new Category();
		category.setName("Wienerbrød");
		return category;
	}
}
