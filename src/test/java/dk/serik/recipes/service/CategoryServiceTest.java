package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.exceptions.ServiceException;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.repository.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryServiceImpl categoryService;
	
	@Mock
	private CategoryJpaRepository categoryJpaRepository;

	@Mock
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		lenient().when(session.getUserName()).thenReturn("Jens");
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
		all.add(dessertToBeSaved());

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
		when(categoryJpaRepository.findById(UUID.fromString("29af0d97-b11c-4ca4-82f5-e938a1234c25"))).thenReturn(Optional.empty());
		assertThat(categoryService.findById("29af0d97-b11c-4ca4-82f5-e938a1234c25")).isEmpty();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	@DisplayName("Given Known Category When FindById Then one is found")
	public void givenKnownCategory_WhenFetchingByKnownId_ThenOneIsFound() {
		when(categoryJpaRepository.findById(UUID.fromString("29af0d97-b11c-4ca4-82f5-e938a1234c25"))).thenReturn(Optional.of(dessertToBeSaved()));
		assertThat(categoryService.findById("29af0d97-b11c-4ca4-82f5-e938a1234c25")).isPresent();
		verify(categoryJpaRepository).findById(any());
	}
	
	@Test
	@DisplayName("Given Known Category When FindByName Then none is found")
	public void givenKnownCategory_WhenFetchingByName_ThenNoneIsFound() {
		lenient().when(categoryJpaRepository.findByName("Vand")).thenReturn(Optional.of(dessertToBeSaved()));  // name: Water
		assertThat(categoryService.findByName("Brød")).isEmpty();
		verify(categoryJpaRepository).findByName("Brød");
	}
	
	@Test
	@DisplayName("Given Known Category When FindByName Then one is found")
	public void givenKnownCategory_WhenFetchingByName_ThenOneIsFound() {
		//Given
		when(categoryJpaRepository.findByName("Dessert")).thenReturn(Optional.of(dessertToBeSaved()));  // name: Dessert

		//When
		assertThat(categoryService.findByName("Dessert")).isPresent();

		//Then
		verify(categoryJpaRepository).findByName(any());
	}

	@Test
	@DisplayName("Given Valid Category DTO When saving Category Then valid category DTO is returned")
	public void givenValidCategoryDTO_WhenSavingCategoryDTO_ThenValidCategoryDtoIsReturned() {
		// Given
		when(categoryJpaRepository.save(any())).thenReturn(savedDessert());

		//When
		CategoryDTO savedDto = categoryService.save(mockToBeSavedDessertDTO());

		//Then
		assertThat(savedDto).isNotNull();
		assertThat(savedDto.getId()).isNotNull();
		assertThat(savedDto.getName()).isEqualTo("Dessert");
		assertThat(savedDto.getCreatedBy()).isEqualTo("Jens");
		verify(categoryJpaRepository, times(1)).save(any());
	}

	@Test
	@DisplayName("Given Valid Category, When updating Description, Then Description is saved ok")
	public void shouldUpdateDescriptionOk() {
		// Given
		when(categoryJpaRepository.findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"))).thenReturn(Optional.of(savedDessert()));

		//When
		CategoryDTO dto = categoryService.update(mockToBeUpdatedDessertDTO());

		// Then
		verify(categoryJpaRepository, Mockito.times(1)).findById(any());
		assertThat(dto.getDescription()).isEqualTo(mockToBeUpdatedDessertDTO().getDescription());
	}

	@Test
	@DisplayName("Given Category do Not Exist, When Updating Description, Then Exception is thrown")
	public void shouldThrowExceptionWhenUpdatingCategoryThatNotExist() {
		// Given
		when(categoryJpaRepository.findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"))).thenReturn(Optional.empty());

		// When
		assertThatThrownBy(() -> categoryService.update(mockToBeUpdatedDessertDTO()))
				.isInstanceOf(ServiceException.class)
				.hasMessageContaining("Category with id 913a5159-3717-4b9d-a290-0158d31ea8aa could not be found");
	}

	@Test
	@DisplayName("Given existing Category, When deleted, Then removed from database")
	public void shouldBeDeletedOk() {
		// Given
		when(categoryJpaRepository.findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"))).thenReturn(Optional.of(savedDessert()));

		// When
		boolean delete = categoryService.delete("913a5159-3717-4b9d-a290-0158d31ea8aa");

		// Then
		assertThat(delete).isTrue();
		verify(categoryJpaRepository, Mockito.times(1)).delete(savedDessert());
		verify(categoryJpaRepository, Mockito.times(1)).findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"));

	}

	@Test
	@DisplayName("Given not existing Category, When deleted, Then false is returned")
	public void shouldNotBeDeleted() {
		// Given
		when(categoryJpaRepository.findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"))).thenReturn(Optional.empty());

		// When
		boolean delete = categoryService.delete("913a5159-3717-4b9d-a290-0158d31ea8aa");

		// Then
		assertThat(delete).isFalse();
		verify(categoryJpaRepository, Mockito.times(1)).findById(UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"));

	}
	
	// utilities, mocks etc.
	
	private Category dessertToBeSaved() {
		Category entity = new Category();
		entity.setName("Dessert");
		entity.setDescription("Den søde afrundning på en god middag");
		ReflectionTestUtils.setField(entity, "id", UUID.fromString("29af0d97-b11c-4ca4-82f5-e938a1234c25"));
		entity.setRecipes(null);
		return entity;
	}

	private Category savedDessert() {
		Category entity = new Category();
		entity.setName("Dessert");
		entity.setDescription("Den søde afrundning på en god middag");
		ReflectionTestUtils.setField(entity, "id", UUID.fromString("913a5159-3717-4b9d-a290-0158d31ea8aa"));
		entity.setRecipes(null);
		entity.setCreatedBy("Jens");
		return entity;
	}

	private CategoryDTO mockToBeSavedDessertDTO() {
		CategoryDTO dto = CategoryDTO.builder()
				.name("Dessert")
				.description("Den søde afrundning på en god middag")
				.id("913a5159-3717-4b9d-a290-0158d31ea8aa").build();
		return dto;
	}

	private CategoryDTO mockToBeUpdatedDessertDTO() {
		CategoryDTO dto = CategoryDTO.builder()
				.name("Dessert")
				.description("Til alle med en sød tand")
				.id("913a5159-3717-4b9d-a290-0158d31ea8aa").build();
		return dto;
	}

	private Category updatedDessert() {
		Category entity = new Category();
		entity.setName("Dessert");
		entity.setDescription("Til alle med en sød tand");
		ReflectionTestUtils.setField(entity, "id", "913a5159-3717-4b9d-a290-0158d31ea8aa");
		entity.setRecipes(null);
		return entity;
	}

}
