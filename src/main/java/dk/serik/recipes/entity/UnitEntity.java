package dk.serik.recipes.entity;

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
@Table(name="unit")
public class UnitEntity extends GenericEntity {
	
	@Column(nullable = false, unique = true)
	private String label;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(mappedBy="unitEntity",  fetch= FetchType.LAZY)
	private Set<RecipeIngredientEntity> recipeIngredientEntities;

	@Override
	public String toString() {
		return "UnitEntity [label=" + label + ", description=" + description + ", recipeIngredientEntities="
				+ recipeIngredientEntities + ", id=" + id + "]";
	}
	
	

}
