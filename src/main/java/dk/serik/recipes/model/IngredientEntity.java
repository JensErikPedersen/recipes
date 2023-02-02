package dk.serik.recipes.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table(name = "ingredient")
public class IngredientEntity extends GenericIdentifierEntity {
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy="ingredientEntity", fetch= FetchType.LAZY)
	private Set<RecipeIngredientEntity> recipeIngredientEntities;

	@Override
	public String toString() {
		return "IngredientEntity [name=" + name + ", description=" + description + ", recipeIngredientEntities="
				+ recipeIngredientEntities + ", id=" + id + "]";
	}

	
	
}
