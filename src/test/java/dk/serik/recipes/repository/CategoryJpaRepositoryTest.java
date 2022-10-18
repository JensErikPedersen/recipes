package dk.serik.recipes.repository;

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
	public void givenCategoryHovedretIsPresent_WhenFetchingCategoryHovedret_ThenOk() {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findByName("Hovedret");
		Assertions.assertTrue(categoryEntity.isPresent());
		Assertions.assertEquals("Majken", categoryEntity.get().getCreatedBy());
	}
	
	@Test
	public void givenCategoryEntityWienerBread_WhenSavingCategory_ThenOk() {
		CategoryEntity categoryEntity = categoryRepository.saveAndFlush(buildWienerBreadEntity());
		log.info("New Category: {}", categoryEntity);
		Assertions.assertEquals("Jens", categoryEntity.getCreatedBy());
		Assertions.assertEquals("Wienerbrød", categoryEntity.getName());
		
	}
	
	
	private CategoryEntity buildWienerBreadEntity() {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName("Wienerbrød");
		return categoryEntity;
	}
}
