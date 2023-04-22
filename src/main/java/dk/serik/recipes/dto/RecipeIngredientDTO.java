package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeIngredientDTO extends BaseDTO{
	private RecipeDTO recipe;

	private IngredientDTO ingredient;

	private BigDecimal amount;

	private UnitDTO unit;

	@Builder
	public RecipeIngredientDTO(RecipeDTO recipe, IngredientDTO ingredient, BigDecimal amount, UnitDTO unit, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String id) {
		super(id, created, createdBy, updated, updatedBy);
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.amount = amount;
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredient.id, recipe.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientDTO other = (RecipeIngredientDTO) obj;
		return Objects.equals(ingredient.id, other.ingredient.id)
				&& Objects.equals(recipe.id, other.recipe.id);
	}





}
