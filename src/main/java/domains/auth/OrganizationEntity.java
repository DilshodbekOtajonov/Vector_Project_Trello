package domains.auth;

import domains.Auditable;
import jakarta.persistence.Table;
import lombok.Builder;

import java.sql.Timestamp;

@Table(name = "organization")
public class OrganizationEntity extends Auditable {
    private String name;
    private long ownerId;
    private long projectId;


    @Builder(builderMethodName = "childBuilder")
    public OrganizationEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String name , long ownerId, long projectId) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.name = name;
        this.ownerId = ownerId;
        this.projectId = projectId;
    }
}
