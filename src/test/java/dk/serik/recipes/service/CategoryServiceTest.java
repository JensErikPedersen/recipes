package dk.serik.recipes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.RecipesTestConfiguration;
import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.CategoryEntity;
import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.repository.CategoryJpaRepository;

@ExtendWith(SpringExtension.class)
@Import(RecipesTestConfiguration.class)
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryMapper categoryMappper;
	
	@MockBean
	private CategoryJpaRepository categoryJpaRepository;
	
	@BeforeEach
	public void setupTest() {
		
	}
	
	@Test
	public void contextIsOk() {
		assertThat(categoryService).isNotNull();
		assertThat(categoryJpaRepository).isNotNull();		
	}
	
	@Test
	public void givenContextOk_WhenNoCategories_ThenListSizeIsZero() {
		when(categoryJpaRepository.findAll()).thenReturn(new ArrayList<>());
		assertThat(categoryService.findAll()).isEmpty();
		verify(categoryJpaRepository, times(1)).findAll();
	}
	
	@Test
	public void givenContextOk_WhenOneCategory_ThenListSizeIsOne() throws Exception{
		List<CategoryEntity> all = new ArrayList<>();
		all.add(mockWater());
		when(categoryJpaRepository.findAll()).thenReturn(all);
		Optional<List<CategoryDTO>> findAll = categoryService.findAll();
		assertThat(findAll).isPresent();
		assertThat(findAll.get().size()).isEqualTo(1);
		verify(categoryJpaRepository, times(1)).findAll();
	}

	
	@Test
	public void givenContextOk_WhenFetchingByUnknwonId_ThenNotFound() {
		when(categoryJpaRepository.findById("0")).thenReturn(Optional.empty());
		assertThat(categoryService.findById("0")).isEmpty();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	public void givenContextOk_WhenFetchingByKnownId_ThenOneIsFound() {
		when(categoryJpaRepository.findById("1")).thenReturn(Optional.of(mockWater()));
		assertThat(categoryService.findById("1")).isPresent();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	public void givenContextOk_WhenFetchingByName_ThenNoneIsFound() {
		when(categoryJpaRepository.findByName("Vand")).thenReturn(Optional.of(mockWater()));  // name: Water
		assertThat(categoryService.findByName("Brød")).isEmpty();
		verify(categoryJpaRepository).findByName("Brød");
	}
	
	@Test
	public void givenContextOk_WhenFetchingByName_ThenOneIsFound() {
		when(categoryJpaRepository.findByName("Vand")).thenReturn(Optional.of(mockWater()));  // name: Water
		assertThat(categoryService.findByName("Vand")).isPresent();
		verify(categoryJpaRepository).findByName(any());
	}
	
	
	
	// TODO: Test coverages
	
	// utilities, mocks etc.
	
	private CategoryEntity mockWater() {
		CategoryEntity entity = new CategoryEntity();
		entity.setName("Vand");
		entity.setDescription("Det er postevand");
		entity.setRecipeEntities(null);
		return entity;
	}

}
