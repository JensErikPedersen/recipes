package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
@Getter
public class RecipeDTO extends GenericDTO {
	private String name;
	
	private String description;
	
	private String instructions;

	@Builder
	public RecipeDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, String instructions) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.instructions = instructions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		RecipeDTO recipeDTO = (RecipeDTO) o;
		return Objects.equals(name, recipeDTO.name) && Objects.equals(description, recipeDTO.description) && Objects.equals(instructions, recipeDTO.instructions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name, description, instructions);
	}
}
