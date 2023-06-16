package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class IngredientDTO extends BaseDTO {
    private String name;
    private String description;

    @Builder
    public IngredientDTO(UUID id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description) {
        super(id, created, createdBy, updated, updatedBy);
        this.name = name;
        this.description = description;
    }

}
