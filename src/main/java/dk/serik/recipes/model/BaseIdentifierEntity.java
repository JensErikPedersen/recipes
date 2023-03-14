package dk.serik.recipes.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@MappedSuperclass
public class BaseIdentifierEntity extends BaseEntity {

	    @Setter(AccessLevel.NONE)
		@Getter(AccessLevel.PUBLIC)
	    @Id
	    @GenericGenerator(name = "generator", strategy = "uuid2")
	    @GeneratedValue(generator = "generator")
	    @Column(updatable = false, nullable = false, columnDefinition = "uniqueidentifier")
	    protected String id;
	   
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        BaseIdentifierEntity that = (BaseIdentifierEntity) o;
	        if(id==null && that.getId()==null) return false;
	        return Objects.equals(id, that.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }

	@Override
	public String toString() {
		return "BaseIdentifierEntity{" +
				"id='" + id + '\'' +
				", created=" + created +
				", createdBy='" + createdBy + '\'' +
				", updated=" + updated +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
