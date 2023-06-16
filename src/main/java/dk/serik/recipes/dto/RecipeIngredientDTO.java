package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeIngredientDTO extends BaseDTO{
	private UUID recipeId;

	private UUID ingredientId;

	private String ingredientName;

	private BigDecimal amount;

	private UUID unitId;
	private String unitLabel;

	@Builder
	public RecipeIngredientDTO(UUID recipeId, UUID ingredientId, String ingredientName, BigDecimal amount, UUID unitId, String unitLabel, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, UUID id) {
		super(id, created, createdBy, updated, updatedBy);
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.unitLabel = unitLabel;
		this.unitId = unitId;
	}

	@Override
	public String toString() {
		return "RecipeIngredientDTO{" +
				"recipeId='" + recipeId + '\'' +
				", ingredientId='" + ingredientId + '\'' +
				", ingredientName='" + ingredientName + '\'' +
				", amount=" + amount +
				", unitId='" + unitId + '\'' +
				", unitLabel='" + unitLabel + '\'' +
				", id='" + id + '\'' +
				", created=" + created +
				", createdBy='" + createdBy + '\'' +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
