package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.mockutil.MockIngredientUtil;
import dk.serik.recipes.model.Ingredient;
import dk.serik.recipes.repository.IngredientJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {
	
	@InjectMocks
	private IngredientServiceImpl service;
	
	@Mock
	private IngredientJpaRepository repository;

	@Mock
	private Session session;

	@BeforeEach
	public void setupTest() {
		lenient().when(session.getUserName()).thenReturn("Jens");
	}

	@Test
	@DisplayName("Context is checked ok")
	public void contextIsOk() {
		assertThat(service).isNotNull();
		assertThat(repository).isNotNull();
	}

	@Test
	@DisplayName("Given 2 ingredients, When fetching all, Then 2 is returned")
	public void shouldFindAllIngredients() {
		// Given
		when(repository.findAll()).thenReturn(mockList());

		// When
		Optional<List<IngredientDTO>> expected = service.findAll();

		// Then
		assertThat(expected).isPresent();
		assertThat(expected.get().size()).isEqualTo(2);
		assertThat(expected).contains(mockDTOList());
		verify(repository, times(1)).findAll();
	}

	@Test
	@DisplayName("Given no ingredients, When fetching all, Then none is returned")
	public void shouldNotReturnAny() {
		// Given
		when(repository.findAll()).thenReturn(new ArrayList<>());

		// When
		Optional<List<IngredientDTO>> expected = service.findAll();

		// Then
		assertThat(expected).isEmpty();
		verify(repository, times(1)).findAll();
	}

	@Test
	@DisplayName("Given existing Ingredient, When fetching by Id, Then Ingredient is returned")
	public void shouldReturnIngredientById() {
		// Given
		when(repository.findById(UUID.fromString("01a50907-8141-4dd1-acdf-c4384669c2b2")))
				.thenReturn(Optional.of(MockIngredientUtil.mockHavsalt()));

		// When
		Optional<IngredientDTO> ingredientDTO = service.findById("01a50907-8141-4dd1-acdf-c4384669c2b2");

		// Then
		verify(repository, times(1)).findById(any());
		assertThat(ingredientDTO).isPresent();
		assertThat(ingredientDTO.get().getName()).isEqualTo("Havsalt");
	}

	@Test
	@DisplayName("Given no Ingredient, When fetching by Id, Then no Ingredient is returned")
	public void shouldReturnNothingById() {
		// Given
		when(repository.findById(UUID.fromString("01a50907-8141-4dd1-acdf-c4384669c2b2")))
				.thenReturn(Optional.empty());

		// When
		Optional<IngredientDTO> ingredientDTO = service.findById("01a50907-8141-4dd1-acdf-c4384669c2b2");

		// Then
		verify(repository, times(1)).findById(any());
		assertThat(ingredientDTO).isEmpty();
	}

	@Test
	@DisplayName("Given existing Ingredient, When fetching by invalid Id, Then exception is thrown")
	public void shouldReturnExceptionWhenFetchingByInvalidId() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.findById("not-valid-uuid");
			}, "Invalid UUID string: not-valid-uuid");
	}

	@Test
	@DisplayName("Given existing Ingredient named 'Havsalt', When fetching by Name, Then Ingredient Havsalt is returned")
	public void shouldReturnIngredientByName() {
		// Given
		when(repository.findByName("Havsalt")).thenReturn(Optional.of(MockIngredientUtil.mockHavsalt()));
		// When
		Optional<IngredientDTO> ingredientDTO = service.findByName("Havsalt");

		//Then
		verify(repository, times(1)).findByName(any());
		assertThat(ingredientDTO.isPresent()).isTrue();
		assertThat(ingredientDTO.get().getName()).isEqualTo("Havsalt");
	}

	@Test
	@DisplayName("Given no Ingredient with Name 'Havsalt', When Fetching by name 'Havsalt', Then no Ingredient is returned")
	public void shouldNotReturnIngredientByName() {
		// Given
		when(repository.findByName("Havsalt")).thenReturn(Optional.empty());

		//When
		Optional<IngredientDTO> ingredientDTO = service.findByName("Havsalt");

		// Then
		assertThat(ingredientDTO.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("Given existing Ingredient, When fetching by name null, Then Optional Empty is Returned")
	public void shouldReturnOptionalEmpty() {
		// When
		Optional<IngredientDTO> ingredientDTO = service.findByName(null);

		// Then
		assertThat(ingredientDTO.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("Given existing ingredient 'Havsalt' and 'Groft salt', When searching by 'salt', Then both Ingredients containing partial name is returned")
	public void shouldReturnBothIngredientsByPartialName() {
		// Given
		given(repository.findAllByNameContains("s")).willReturn(Optional.of(mockList()));

		//When
		Optional<List<IngredientDTO>> ingredientDTOS = service.findAllByNameContains("s");

		// Then
		then(repository).should(times(1)).findAllByNameContains(any());
		assertThat(ingredientDTOS.isPresent()).isTrue();
		assertThat(ingredientDTOS.get().size()).isEqualTo(2);
		assertThat(ingredientDTOS).contains(mockDTOList());
	}

	@Test
	@DisplayName("Given existing Ingredients exist, When searching for partial name not found in any, Then Empty is returned")
	public void shouldReturnEmpty() {
		// Given
		given(repository.findAllByNameContains("mel")).willReturn(Optional.empty());

		// When
		Optional<List<IngredientDTO>> ingredientDTOS = service.findAllByNameContains("mel");

		// Then
		then(repository).should(times(1)).findAllByNameContains("mel");
		assertThat(ingredientDTOS.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("Given existing Ingredients, When searching will null, Then Empty is returned")
	public void shouldReturnEmptyWhenSearchingWithNull() {

	}


	private List<Ingredient> mockList() {
		List<Ingredient> mocks = new ArrayList<>();
		mocks.add(MockIngredientUtil.mockHvedemel());
		mocks.add(MockIngredientUtil.mockHavsalt());
		return mocks;
	}


	private List<IngredientDTO> mockDTOList() {
		List<IngredientDTO> mocks = new ArrayList<>();
		mocks.add(MockIngredientUtil.mockHvedemelDTO());
		mocks.add(MockIngredientUtil.mockHavsaltDTO());
		return mocks;
	}
}
