package dk.serik.recipes.service;

import java.util.List;
import java.util.Optional;

import dk.serik.recipes.dto.CategoryDTO;

public interface CategoryService {

	Optional<List<CategoryDTO>> findAll();

	Optional<CategoryDTO> findById(String string);

	Optional<CategoryDTO> findByName(String string);
	
	CategoryDTO save(CategoryDTO categoryDto);

	void delete(String id);

	CategoryDTO update(CategoryDTO dto);

}
