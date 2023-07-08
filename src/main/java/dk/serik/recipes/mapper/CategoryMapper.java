package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;

import java.util.Objects;

public class CategoryMapper {

    public static CategoryDTO from(Category entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        CategoryDTO dto = CategoryDTO.builder()
                .description(entity.getDescription())
                .name(entity.getName())
                .id(entity.getId().toString())
                .updatedBy(entity.getUpdatedBy())
                .updated(entity.getUpdated())
                .created(entity.getCreated())
                .createdBy(entity.getCreatedBy())
                .build();
        return dto;
    }

}
