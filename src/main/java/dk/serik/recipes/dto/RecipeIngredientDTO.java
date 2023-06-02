package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeIngredientDTO extends BaseDTO{
	private String recipeId;

	private String ingredientId;

	private String ingredientName;

	private BigDecimal amount;

	private String unitId;
	private String unitLabel;

	@Builder
	public RecipeIngredientDTO(String recipeId, String ingredientId, String ingredientName, BigDecimal amount, String unitId, String unitLabel, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String id) {
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
