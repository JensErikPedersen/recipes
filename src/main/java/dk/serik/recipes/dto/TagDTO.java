package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TagDTO extends BaseDTO {

    private String name;

    @Builder
    public TagDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name) {
        super(id, created, createdBy, updated, updatedBy);
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "label='" + name + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
