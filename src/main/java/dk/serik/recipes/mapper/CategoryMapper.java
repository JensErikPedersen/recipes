package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;

import java.util.Objects;

public class CategoryMapper {

    public static CategoryDTO from(Category category) {
        if(Objects.isNull(category)) {
            return null;
        }
        CategoryDTO dto = CategoryDTO.builder()
                .description(category.getDescription())
                .name(category.getName())
                .id(category.getId())
                .updatedBy(category.getUpdatedBy())
                .updated(category.getUpdated())
                .created(category.getCreated())
                .createdBy(category.getCreatedBy())
                .build();
        return dto;
    }

}
