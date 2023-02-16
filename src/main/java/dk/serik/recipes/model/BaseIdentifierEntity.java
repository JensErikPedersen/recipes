package dk.serik.recipes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@MappedSuperclass
public class BaseIdentifierEntity extends BaseEntity {

	    @Setter(AccessLevel.NONE)	    
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
}
