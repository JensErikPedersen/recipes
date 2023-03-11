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
@Table(name = "recipe")
public class Recipe extends BaseIdentifierEntity {
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private String description;
	
	private String instructions;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	@OneToMany(mappedBy= "recipe", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeIngredient> recipeIngredientEntities;
	
	@OneToMany(mappedBy= "recipe", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeRating> recipeRatingEntities;

	@OneToMany(mappedBy= "recipe", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeTag> recipeTagEntities;

	@Override
	public String toString() {
		return "RecipeEntity [name=" + name + ", description=" + description + ", instructions=" + instructions
				+ ", categoryEntity=" + category
//				+ ", recipeIngredientEntities=" + recipeIngredientEntities
				+ ", id=" + id + "]";
	}
	
	
	
}
