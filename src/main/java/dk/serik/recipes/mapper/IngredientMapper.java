package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.model.Ingredient;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class IngredientMapper {

    public static IngredientDTO from(Ingredient entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        IngredientDTO dto = IngredientDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .created(entity.getCreated())
                .updatedBy(entity.getUpdatedBy())
                .updatedBy(entity.getUpdatedBy())
                .build();
        log.info("Mapped DTO: {}", dto);
        return dto;
    }
}
