package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
public class RecipeDTO extends BaseDTO {
	private String name;
	
	private String description;
	
	private String instructions;

	private String categoryId;

	private String categoryName;

	private List<RecipeIngredientDTO> recipeIngredients;

	private List<RecipeRatingDTO> recipeRatings;

	private List<TagDTO> tags;

	@Builder
	public RecipeDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, String instructions, String categoryId, String categoryName, List<RecipeIngredientDTO> recipeIngredients, List<RecipeRatingDTO> recipeRatings, List<TagDTO> tags) {
		super(id, created, createdBy, updated, updatedBy);
		this.name = name;
		this.description = description;
		this.instructions = instructions;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.recipeIngredients = recipeIngredients;
		this.recipeRatings = recipeRatings;
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "RecipeDTO{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", instructions='" + instructions + '\'' +
				", categoryId='" + categoryId + '\'' +
				", categoryName='" + categoryName + '\'' +
				", recipeIngredients=" + recipeIngredients +
				", recipeRatings=" + recipeRatings +
				", tags=" + tags +
				", id='" + id + '\'' +
				", created=" + created +
				", createdBy='" + createdBy + '\'' +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
