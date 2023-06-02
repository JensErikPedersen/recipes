package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TagDTO extends BaseDTO {

    private String label;

    @Builder
    public TagDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String label) {
        super(id, created, createdBy, updated, updatedBy);
        this.label = label;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "label='" + label + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
