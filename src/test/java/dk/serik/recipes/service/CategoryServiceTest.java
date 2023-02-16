package dk.serik.recipes.service;

import dk.serik.recipes.MapperTestConfiguration;
import dk.serik.recipes.RecipesTestConfiguration;
import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.repository.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({RecipesTestConfiguration.class, MapperTestConfiguration.class})
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
	@DisplayName("Context is checked ok")
	public void contextIsOk() {
		assertThat(categoryService).isNotNull();
		assertThat(categoryJpaRepository).isNotNull();		
	}
	
	@Test
	@DisplayName("Given No Categories When FindAll Then list size is Zero")
	public void givenNoCategories_WhenFindAll_ThenListSizeIsZero() {
		when(categoryJpaRepository.findAll()).thenReturn(new ArrayList<>());
		assertThat(categoryService.findAll()).isEmpty();
		verify(categoryJpaRepository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Given No Categories When FindAll Then list size is One")
	public void givenOneCategory_WhenFindAll_ThenListSizeIsOne() throws Exception{

		// given
		List<Category> all = new ArrayList<>();
		all.add(mockWater());

		// when
		when(categoryJpaRepository.findAll()).thenReturn(all);
		Optional<List<CategoryDTO>> findAll = categoryService.findAll();

		// then
		assertThat(findAll).isPresent();
		assertThat(findAll.get().size()).isEqualTo(1);
		verify(categoryJpaRepository, times(1)).findAll();
	}

	
	@Test
	@DisplayName("Given No Categories When FindById Then none is found")
	public void givenNoCatregories_WhenFetchingByUnknwonId_ThenNotFound() {
		when(categoryJpaRepository.findById("0")).thenReturn(Optional.empty());
		assertThat(categoryService.findById("0")).isEmpty();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	@DisplayName("Given Known Category When FindById Then one is found")
	public void givenKnownCategory_WhenFetchingByKnownId_ThenOneIsFound() {
		when(categoryJpaRepository.findById("1")).thenReturn(Optional.of(mockWater()));
		assertThat(categoryService.findById("1")).isPresent();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	@DisplayName("Given Known Category When FindByName Then none is found")
	public void givenKnownCategory_WhenFetchingByName_ThenNoneIsFound() {
		when(categoryJpaRepository.findByName("Vand")).thenReturn(Optional.of(mockWater()));  // name: Water
		assertThat(categoryService.findByName("Brød")).isEmpty();
		verify(categoryJpaRepository).findByName("Brød");
	}
	
	@Test
	@DisplayName("Given Known Category When FindByName Then one is found")
	public void givenKnownCategory_WhenFetchingByName_ThenOneIsFound() {
		when(categoryJpaRepository.findByName("Vand")).thenReturn(Optional.of(mockWater()));  // name: Water
		assertThat(categoryService.findByName("Vand")).isPresent();
		verify(categoryJpaRepository).findByName(any());
	}

	@Test
	@DisplayName("Given Valid Category DTO When saving Category Then valid category DTO is returned")
	public void givenValidCategoryDTO_WhenSavingCategoryDTO_ThenValidCategoryDtoIsReturned() {
		when(categoryJpaRepository.saveAndFlush(any())).thenReturn(savedMockWater());
		CategoryDTO savedDto = categoryService.save(mockToBeSavedWaterDTO());
		assertThat(savedDto.getId()).isEqualTo("1234");
		verify(categoryJpaRepository, times(1)).saveAndFlush(any());
	}
	
	
	// TODO: Test coverages
	
	// utilities, mocks etc.
	
	private Category mockWater() {
		Category entity = new Category();
		entity.setName("Vand");
		entity.setDescription("Det er postevand");
		entity.setRecipeEntities(null);
		return entity;
	}

	private Category savedMockWater() {
		Category entity = new Category();
		entity.setName("Vand");
		entity.setDescription("Det er postevand");
		ReflectionTestUtils.setField(entity, "id", "1234");
		entity.setRecipeEntities(null);
		return entity;
	}

	private CategoryDTO mockSavedWaterDTO() {
		CategoryDTO dto = CategoryDTO.builder()
				.name("Vand")
				.description("Det er postevand")
				.recipeEntities(null)
				.id("1234").build();
		return dto;
	}

	private CategoryDTO mockToBeSavedWaterDTO() {
		CategoryDTO dto = CategoryDTO.builder()
				.name("Vand")
				.description("Det er postevand")
				.recipeEntities(null).build();
		return dto;
	}

}
