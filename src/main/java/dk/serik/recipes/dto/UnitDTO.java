package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.serik.recipes.model.RecipeIngredient;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UnitDTO extends BaseDTO {

    private String label;

    private String description;

    @Builder
    public UnitDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String label, String description) {
        super(id, created, createdBy, updated, updatedBy);
        this.label = label;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
