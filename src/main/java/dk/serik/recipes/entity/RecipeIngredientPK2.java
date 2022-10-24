package dk.serik.recipes.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class RecipeIngredientPK2 implements Serializable {
	
//	private RecipeEntity recipeEntity;
//	
//	private IngredientEntity ingredientEntity;
	
	@Column(name = "recipe_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID recipeId;
	
	@Column(name="ingredient_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID ingredientId;

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId, recipeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientPK2 other = (RecipeIngredientPK2) obj;
		return Objects.equals(ingredientId, other.ingredientId) && Objects.equals(recipeId, other.recipeId);
	}
	
	

//	@Override
//	public int hashCode() {
//		return Objects.hash(ingredientEntity, recipeEntity);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		RecipeIngredientKey other = (RecipeIngredientKey) obj;
//		return Objects.equals(ingredientEntity, other.ingredientEntity)
//				&& Objects.equals(recipeEntity, other.recipeEntity);
//	}
	
	
	

//	@Transient
//	private static final long serialVersionUID = -1437096901351465476L;
//	
//	@Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//	@Type(type = "uuid-char")
//	private UUID recipeId;
//	private RecipeEntity recipeEntity;
	    
//    @Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//    @Type(type = "uuid-char")
//    private UUID ingredientId;
//    
//	private IngredientEntity ingredientEntity;
    
//    public RecipeIngredientKey(UUID recipeId, UUID ingredientId) {
//    	log.info("Setting recipeId: {} and ingredientId: {}", recipeId, ingredientId);
//    	this.recipeId = recipeId;
//    	this.ingredientId = ingredientId;
//    }
//
//    public RecipeIngredientKey() {}
//    
//	public UUID getRecipeId() {
//		return recipeId;
//	}
//
//	public void setRecipeId(UUID recipeId) {
//		log.info("Set recipeId: {}", recipeId);
//		this.recipeId = recipeId;
//	}
//
//	public UUID getIngredientId() {
//		return ingredientId;
//	}
//
//	public void setIngredientId(UUID ingredientId) {
//		log.info("Set ingredientId: {}", ingredientId);
//		this.ingredientId = ingredientId;
//	}
//
//	@Override
//	public String toString() {
//		return "RecipeIngredientKey [recipeId=" + recipeId + ", ingredientId=" + ingredientId + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(ingredientId, recipeId);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		RecipeIngredientKey other = (RecipeIngredientKey) obj;
//		return Objects.equals(ingredientId, other.ingredientId) && Objects.equals(recipeId, other.recipeId);
//	}
//
//    
	
	
    
}
