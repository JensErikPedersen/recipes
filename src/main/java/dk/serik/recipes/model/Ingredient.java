package dk.serik.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient")
public class Ingredient extends BaseIdentifierEntity {
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy= "ingredient", fetch= FetchType.LAZY)
	private Set<RecipeIngredient> recipeIngredientEntities;

	@Override
	public String toString() {
		return "IngredientEntity [name=" + name + ", description=" + description + ", recipeIngredientEntities="
				+ recipeIngredientEntities + ", id=" + id + "]";
	}

	
	
}