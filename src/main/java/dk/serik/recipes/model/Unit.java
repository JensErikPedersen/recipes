package dk.serik.recipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="unit")
public class Unit extends BaseIdentifierEntity {
	
	@Column(nullable = false, unique = true)
	private String label;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy= "unit",  fetch= FetchType.LAZY)
	private Set<RecipeIngredient> recipeIngredientEntities;

	@Override
	public String toString() {
		return "UnitEntity [label=" + label + ", description=" + name + ", recipeIngredientEntities="
				+ recipeIngredientEntities + ", id=" + id + "]";
	}
	
	

}
