package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RecipeDTO;
import dk.serik.recipes.dto.RecipeIngredientDTO;
import dk.serik.recipes.dto.RecipeRatingDTO;
import dk.serik.recipes.dto.TagDTO;
import dk.serik.recipes.model.Recipe;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class RecipeMapper {
    public static RecipeDTO from(Recipe entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        RecipeDTO dto = RecipeDTO.builder()
                .description(entity.getDescription())
                .instructions(entity.getInstructions())
                .name(entity.getName())
                .id(entity.getId().toString())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .recipeIngredients(entity.getRecipeIngredients().stream()
                        .map(ri -> {
                            RecipeIngredientDTO riDto = RecipeIngredientMapper.from(ri);
                            log.info("Mapped DTO: {}", riDto);
                            return riDto;
                        }).collect(Collectors.toList())
                ).recipeRatings(entity.getRecipeRatings().stream()
                        .map(rr -> {
                            RecipeRatingDTO rrDto = RecipeRatingMapper.from(rr);
                            log.info("Mapped DTO: {}", rrDto);
                            return rrDto;
                        }).collect(Collectors.toList())
                ).tags(entity.getTags().stream()
                        .map(t -> {
                            TagDTO tDto = TagMapper.from(t);
                            log.info("Mapped DTO: {}", tDto);
                            return tDto;
                            }).collect(Collectors.toList()))
                .build();
            log.info("Mapped RecipeDTO: {}", dto);
            return dto;
    }
}
