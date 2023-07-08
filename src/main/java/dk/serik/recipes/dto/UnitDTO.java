package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UnitDTO extends BaseDTO {

    private String name;

    private String description;

    @Builder
    public UnitDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description) {
        super(id, created, createdBy, updated, updatedBy);
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
                "label='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
