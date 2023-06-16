package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeRatingDTO extends BaseDTO {
	private UUID recipeId;
	private UUID ratingId;
	private Integer rating;
	private String description;

	@Builder
	public RecipeRatingDTO(UUID id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, UUID recipeId, UUID ratingId, Integer rating, String description) {
		super(id, created, createdBy, updated, updatedBy);
		this.recipeId = recipeId;
		this.rating = rating;
		this.ratingId = ratingId;
		this.description = description;
	}

	@Override
	public String toString() {
		return "RecipeRatingDTO{" +
				"recipeId='" + recipeId + '\'' +
				", ratingId='" + ratingId + '\'' +
				", rating=" + rating +
				", description='" + description + '\'' +
				", id='" + id + '\'' +
				", created=" + created +
				", createdBy='" + createdBy + '\'' +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
