package dk.serik.recipes.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RecipeIngredientPK.class)
@Table(name = "recipe_ingredient")
public class RecipeIngredientEntity extends GenericEntity {	

	@Id	
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable=false)  
	private RecipeEntity recipeEntity;
	
	@Id
	@ManyToOne
	@JoinColumn(name="ingredient_id", nullable = false)
	private IngredientEntity ingredientEntity;
	
	@Column(nullable=false)
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name="unit_id", nullable=false)
	private UnitEntity unitEntity;

  	
	@Override
	public int hashCode() {
		return Objects.hash(ingredientEntity.id, recipeEntity.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientEntity other = (RecipeIngredientEntity) obj;
		return Objects.equals(ingredientEntity.id, other.ingredientEntity.id)
				&& Objects.equals(recipeEntity.id, other.recipeEntity.id);
	}

	@Override
	public String toString() {
		return "RecipeIngredientEntity [recipeEntity=" + recipeEntity + ", ingredientEntity=" + ingredientEntity
				+ ", amount=" + amount + ", unitEntity=" + unitEntity + "]";
	}



}
