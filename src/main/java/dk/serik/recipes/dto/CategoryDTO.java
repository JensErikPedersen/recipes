package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@JsonInclude(Include.NON_NULL)
@Getter
public class CategoryDTO extends BaseDTO {
	private String name;
	private String description;
	private Set<RecipeDTO> recipes;

	@Builder
	public CategoryDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, Set<RecipeDTO> recipes) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.recipes = recipes;
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
				", recipeEntities=" + recipes +
				'}';
	}

}
