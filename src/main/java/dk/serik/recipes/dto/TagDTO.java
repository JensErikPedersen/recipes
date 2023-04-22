package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TagDTO extends BaseDTO {

    private String label;

    private Set<RecipeDTO> recipes;

    @Builder
    public TagDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String label, Set<RecipeDTO> recipes) {
        super(id, created, createdBy, updated, updatedBy);
        this.label = label;
        this.recipes = recipes;
    }
}
