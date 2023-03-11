package dk.serik.recipes.model;

import dk.serik.recipes.bean.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityListener {
	
	private Session session;

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedBy(session.getUserName());
        log.info("createdBy is set to: {}", session.getUserName());
    }

    @PostPersist
    public void postPersist(BaseEntity baseEntity) {
        //log.info(sessionAware.getSessionId() + " : postPersist(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PreRemove
    public void preRemove(BaseEntity baseEntity) {
        //log.info(sessionAware.getSessionId() + " : preRemove(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PostRemove
    public void postRemove(BaseEntity baseEntity) {
        //log.info(sessionAware.getSessionId() + " : postRemove(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedBy(session.getUserName());
        log.info("updateBy is set to: {}", session.getUserName());
    }

    @PostUpdate
    public void postUpdate(BaseEntity baseEntity) {
        //log.info(sessionAware.getSessionId() + " : postUpdate(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PostLoad
    public void postLoad(BaseEntity baseEntity) {
        //log.info(sessionAware.getSessionId() + " : postLoad(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
}
