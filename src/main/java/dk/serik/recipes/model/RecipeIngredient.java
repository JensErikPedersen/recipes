package dk.serik.recipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RecipeIngredientPK.class)
@Table(name = "recipe_ingredient")
public class RecipeIngredient extends BaseEntity {
	@Id
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable=false)  
	private Recipe recipe;

	@Id
	@ManyToOne
	@JoinColumn(name="ingredient_id", nullable = false)
	private Ingredient ingredient;

	@Column(nullable=false)
	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name="unit_id", nullable=false)
	private Unit unit;
  	
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
		RecipeIngredient other = (RecipeIngredient) obj;
		return Objects.equals(ingredient.id, other.ingredient.id)
				&& Objects.equals(recipe.id, other.recipe.id);
	}

	@Override
	public String toString() {
		return "RecipeIngredient{" +
				"recipe=" + recipe +
				", ingredient=" + ingredient +
				", amount=" + amount +
				", unit=" + unit +
				", created=" + created +
				", createdBy='" + createdBy + '\'' +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
