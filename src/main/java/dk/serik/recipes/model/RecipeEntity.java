package dk.serik.recipes.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recipe")
public class RecipeEntity extends GenericIdentifierEntity {
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private String description;
	
	private String instructions;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private CategoryEntity categoryEntity;
	
	@OneToMany(mappedBy="recipeEntity", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeIngredientEntity> recipeIngredientEntities;
	
	@OneToMany(mappedBy="recipeEntity", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeRatingEntity> recipeRatingEntities;

	@OneToMany(mappedBy="recipeEntity", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipeTagEntity> recipeTagEntities;

	@Override
	public String toString() {
		return "RecipeEntity [name=" + name + ", description=" + description + ", instructions=" + instructions
				+ ", categoryEntity=" + categoryEntity 
//				+ ", recipeIngredientEntities=" + recipeIngredientEntities
				+ ", id=" + id + "]";
	}
	
	
	
}
