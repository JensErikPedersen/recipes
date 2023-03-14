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
		StringBuilder sb = new StringBuilder();
		sb.append("Recipe{");
		sb.append(", name='" + name + '\'');
		sb.append(	", description='" + description + '\'');
		sb.append(", instructions='" + instructions + '\'');
		sb.append(	", category=" + category);
		recipeIngredientEntities.forEach(ri -> sb.append("Ingredient: " + ri.getIngredient().getName() + ", Amount: " + ri.getAmount() + " " + ri.getUnit().getLabel() + ", "));
		recipeRatingEntities.forEach(rr -> sb.append("Rating: " + rr.getRating().getRating()));
		recipeTagEntities.forEach(rt -> sb.append("Tag: " + rt.getTag().getLabel()));
		sb.append(", id='" + id + '\'');
		sb.append(", created=" + created);
		sb.append(", createdBy='" + createdBy + '\'');
		sb.append(", updated=" + updated );
		sb.append(", updatedBy='" + updatedBy + '\'');
		sb.append('}');
		return sb.toString();
	}
}
