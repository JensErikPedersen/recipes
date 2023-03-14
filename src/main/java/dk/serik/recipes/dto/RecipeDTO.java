package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.model.RecipeIngredient;
import dk.serik.recipes.model.RecipeRating;
import dk.serik.recipes.model.RecipeTag;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@JsonInclude(Include.NON_NULL)
@Getter
public class RecipeDTO extends GenericDTO {
	private String name;
	
	private String description;
	
	private String instructions;

	private Category category;
	private Set<RecipeIngredient> recipeIngredientEntities;

	private Set<RecipeRating> recipeRatingEntities;

	private Set<RecipeTag> recipeTagEntities;


	@Builder
	public RecipeDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, String instructions, Category category, Set<RecipeIngredient> recipeIngredientEntities, Set<RecipeRating> recipeRatingEntities, Set<RecipeTag> recipeTagEntities) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.instructions = instructions;
		this.category = category;
		this.recipeIngredientEntities = recipeIngredientEntities;
		this.recipeRatingEntities = recipeRatingEntities;
		this.recipeTagEntities = recipeTagEntities;
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
