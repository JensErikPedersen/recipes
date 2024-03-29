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
@Table(name="category")
public class Category extends BaseIdentifierEntity {

	@Column(nullable = false, unique = true)
	private String name;

	private String description;	
	
	 @OneToMany(mappedBy= "category", fetch= FetchType.LAZY)
	 private Set<Recipe> recipes;

	@Override
	public String toString() {
		return "CategoryEntity [name=" + name + ", description=" + description + ", id=" + id + "]";
	}

}
