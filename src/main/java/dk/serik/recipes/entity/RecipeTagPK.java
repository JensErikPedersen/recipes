package dk.serik.recipes.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeTagPK implements Serializable {
	
	private String recipeEntity;
	
	private String tagEntity;

	@Override
	public int hashCode() {
		return Objects.hash(recipeEntity, tagEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeTagPK other = (RecipeTagPK) obj;
		return Objects.equals(recipeEntity, other.recipeEntity) && Objects.equals(tagEntity, other.tagEntity);
	}

	
}
