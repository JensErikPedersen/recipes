package dk.serik.recipes.entity;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Data
@MappedSuperclass
@EntityListeners(GenericEntityListener.class)
public class GenericEntity implements Comparable {

	    @Transient
	    protected final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

	    @Setter(AccessLevel.NONE)	    
	    @Id
	    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	    @GeneratedValue(generator = "uuid2")
	    //@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	    @Type(type = "uuid-char")
	    protected UUID id;

	    @Setter(AccessLevel.PRIVATE)	    
	    //@Column(name = "created", nullable = false)
	    @Column(nullable = false)
	    protected OffsetDateTime created;

	    //@Column(name = "created_by", nullable = false)
	    @Column(nullable = false)
	    protected String createdBy;

	    @Setter(AccessLevel.PRIVATE)
	   // @Column(name = "updated")
	    protected OffsetDateTime updated;

	   // @Column(name = "updated_by")
	    protected String updatedBy;

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        GenericEntity that = (GenericEntity) o;
	        if(id==null && that.id==null) return false;
	        return Objects.equals(id, that.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }

	    @PrePersist
	    public void prePersist() {
	        setCreated(OffsetDateTime.now());
	    }
	    @PostPersist
	    public void postPersist() {
	    }
	    @PreRemove
	    public void preRemove() {
	    }
	    @PostRemove
	    public void postRemove() {
	    }
	    @PreUpdate
	    public void preUpdate() {
	    	setUpdated(OffsetDateTime.now());	        
	    }
	    @PostUpdate
	    public void postUpdate() {
	    }
	    @PostLoad
	    public void postLoad() {
	    }

	    @Override
	    public int compareTo(Object o) {
	        if(o!=null && o instanceof GenericEntity && created!=null && ((GenericEntity) o).created!=null) {
	            return created.compareTo(((GenericEntity) o).created);
	        }
	        return -1;
	    }
}
