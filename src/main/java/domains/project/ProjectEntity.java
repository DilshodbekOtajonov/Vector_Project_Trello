package domains.project;

import domains.Auditable;
import enums.ProjectStatus;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 10:57 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
@Getter
@Setter
@Table(name = "project")
public class ProjectEntity extends Auditable {
    private String title;
    private String description;
    private String docPath;
    private ProjectStatus status;

    private List<ProjectColumnEntity> projectColumns;

    @Builder(builderMethodName = "childBuilder")
    public ProjectEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String docPath, ProjectStatus status, List<ProjectColumnEntity> projectColumns) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.docPath = docPath;
        this.status = status;
        this.projectColumns = projectColumns;
    }


}
