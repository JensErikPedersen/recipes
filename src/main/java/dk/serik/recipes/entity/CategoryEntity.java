package dk.serik.recipes.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class CategoryEntity extends GenericIdentifierEntity {
	
    @Setter(AccessLevel.NONE)	    
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    @Column(updatable = false, nullable = false, columnDefinition = "uniqueidentifier")	   
    protected String id;
	
	@Column(nullable = false, unique = true)
	private String name;

	private String description;	
	
	 @OneToMany(mappedBy="categoryEntity", fetch= FetchType.LAZY)
	 private Set<RecipeEntity> recipeEntities;

	@Override
	public String toString() {
		return "CategoryEntity [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
  
	
}
