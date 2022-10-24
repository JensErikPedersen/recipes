package dk.serik.recipes.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class RecipeIngredientEntity {
	

	@Id	
	@ManyToOne
//	@MapsId("recipeId")
	@JoinColumn(name="recipe_id", nullable=false)  
	private RecipeEntity recipeId;
	
	@Id
	@ManyToOne
//	@MapsId("ingredientId")
	@JoinColumn(name="ingredient_id", nullable = false)
	private IngredientEntity ingredientId;
	
	@Column(nullable=false)
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name="unit_id", nullable=false)
	private UnitEntity unitEntity;

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId.id, recipeId.id);
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
		return Objects.equals(ingredientId.id, other.ingredientId.id)
				&& Objects.equals(recipeId.id, other.recipeId.id);
	}

	@Override
	public String toString() {
		return "RecipeIngredientEntity [recipeEntity=" + recipeId + ", ingredientEntity=" + ingredientId
				+ ", amount=" + amount + ", unitEntity=" + unitEntity + "]";
	}



}
