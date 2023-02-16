package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
	
	public CategoryDTO fromEntity(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setDescription(entity.getDescription());
		dto.setName(entity.getName());
		dto.setUpdated(entity.getUpdated());
		dto.setUpdatedBy(entity.getUpdatedBy());
		return dto;
	}

	public Category fromDto(CategoryDTO dto) {
		Category entity = new Category();
		entity.setDescription(dto.getDescription());
		entity.setName(dto.getName());
		return entity;
	}

}
