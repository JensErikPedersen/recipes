package dk.serik.recipes.mapper;

import org.springframework.stereotype.Component;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.CategoryEntity;

@Component
public class CategoryMapper {
	
	public CategoryDTO from(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setDescription(entity.getDescription());
		dto.setName(entity.getName());
//		List<RecipeDTO> dtos = entity.getRecipeEntities().stream()
//				.map(e -> )
		dto.setUpdated(entity.getUpdated());
		dto.setUpdatedBy(entity.getUpdatedBy());
		return dto;
	}

}
