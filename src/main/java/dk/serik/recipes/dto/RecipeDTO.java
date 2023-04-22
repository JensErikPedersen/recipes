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
public class RecipeDTO extends BaseDTO {
	private String name;
	
	private String description;
	
	private String instructions;

	private CategoryDTO category;
	private Set<RecipeIngredientDTO> recipeIngredients;

	private Set<RecipeRatingDTO> recipeRatings;

	private Set<TagDTO> tags;

	@Builder
	public RecipeDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, String instructions, CategoryDTO category, Set<RecipeIngredientDTO> recipeIngredients, Set<RecipeRatingDTO> recipeRatings, Set<TagDTO> tags) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.instructions = instructions;
		this.category = category;
		this.recipeIngredients = recipeIngredients;
		this.recipeRatings = recipeRatings;
		this.tags = tags;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		RecipeDTO recipeDTO = (RecipeDTO) o;
		return Objects.equals(name, recipeDTO.name) && Objects.equals(description, recipeDTO.description) && Objects.equals(instructions, recipeDTO.instructions) && Objects.equals(category, recipeDTO.category) && Objects.equals(recipeIngredients, recipeDTO.recipeIngredients) && Objects.equals(recipeRatings, recipeDTO.recipeRatings) && Objects.equals(tags, recipeDTO.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name, description, instructions, category, recipeIngredients, recipeRatings, tags);
	}
}
