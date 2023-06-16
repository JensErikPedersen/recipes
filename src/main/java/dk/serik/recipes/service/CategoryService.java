package dk.serik.recipes.service;

import dk.serik.recipes.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

	Optional<List<CategoryDTO>> findAll();

	Optional<CategoryDTO> findById(UUID string);

	Optional<CategoryDTO> findByName(String string);
	
	CategoryDTO save(CategoryDTO categoryDto);

	void delete(UUID id);

	CategoryDTO update(CategoryDTO dto);

}
