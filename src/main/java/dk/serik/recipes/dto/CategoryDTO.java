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
public class CategoryDTO extends GenericDTO {
	private String name;
	private String description;
	private Set<RecipeDTO> recipeEntities;

	@Builder
	public CategoryDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, Set<RecipeDTO> recipeEntities) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.recipeEntities = recipeEntities;
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
				", recipeEntities=" + recipeEntities +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		CategoryDTO that = (CategoryDTO) o;
		return id.equals(that.id) && created.equals(that.created) && updated.equals(that.updated) && updatedBy.equals(that.updatedBy) && createdBy.equals(that.createdBy) && name.equals(that.name) && Objects.equals(description, that.description) && Objects.equals(recipeEntities, that.recipeEntities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, created, updated, updatedBy, createdBy, name, description, recipeEntities);
	}
}
