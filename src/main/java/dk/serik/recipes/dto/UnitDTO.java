package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UnitDTO extends BaseDTO {

    private String label;

    private String name;

    @Builder
    public UnitDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String label, String name) {
        super(id, created, createdBy, updated, updatedBy);
        this.label = label;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
                "label='" + label + '\'' +
                ", description='" + name + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
