package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import org.springframework.stereotype.Component;

public class CategoryMapper {
	
	public static CategoryDTO fromEntity(Category entity) {
		CategoryDTO dto = CategoryDTO.builder()
				.id(entity.getId())
				.created(entity.getCreated())
				.createdBy(entity.getCreatedBy())
				.description(entity.getDescription())
				.name(entity.getName())
				.updated(entity.getUpdated())
				.updatedBy(entity.getUpdatedBy())
				.build();
		return dto;
	}

	public static Category fromDto(CategoryDTO dto) {
		Category entity = new Category();
		entity.setDescription(dto.getDescription());
		entity.setName(dto.getName());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setUpdatedBy(dto.getUpdatedBy());
		return entity;
	}

}
