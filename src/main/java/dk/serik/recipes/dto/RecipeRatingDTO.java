package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.serik.recipes.model.BaseIdentifierEntity;
import dk.serik.recipes.model.Rating;
import dk.serik.recipes.model.Recipe;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.OffsetDateTime;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeRatingDTO extends BaseDTO {
	private RecipeDTO recipe;
	private RatingDTO rating;
	private String description;

	@Builder
	public RecipeRatingDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, RecipeDTO recipe, RatingDTO rating, String description) {
		super(id, created, createdBy, updated, updatedBy);
		this.recipe = recipe;
		this.rating = rating;
		this.description = description;
	}
}
