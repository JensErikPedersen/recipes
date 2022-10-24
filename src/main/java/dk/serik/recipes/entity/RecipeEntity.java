package dk.serik.recipes.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
//@EqualsAndHashCode(callSuper=true)
//@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class RecipeEntity extends GenericEntity {
	
	@Column(nullable = false, updatable = true)
	private String name;
	
	private String description;
	
	private String instructions;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private CategoryEntity categoryEntity;
	
	@OneToMany(mappedBy="recipeId", fetch= FetchType.LAZY)
	private Set<RecipeIngredientEntity> recipeIngredientEntities;


	@Override
	public String toString() {
		return "RecipeEntity [name=" + name + ", description=" + description + ", instructions=" + instructions
				+ ", categoryEntity=" + categoryEntity 
//				+ ", recipeIngredientEntities=" + recipeIngredientEntities
				+ ", id=" + id + "]";
	}
	
	
	
}
