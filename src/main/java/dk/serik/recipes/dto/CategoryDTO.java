package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonInclude(Include.NON_NULL)
@Getter
public class CategoryDTO extends BaseDTO {
	private String name;
	private String description;

	@Builder
	public CategoryDTO(UUID id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return "CategoryDTO{" +
				"id='" + id + '\'' +
				", created=" + created +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				", createdBy='" + createdBy + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}

}
