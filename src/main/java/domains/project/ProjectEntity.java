package domains.project;

import domains.Auditable;
import enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 10:57 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
@Getter
@Setter
public class ProjectEntity extends Auditable {
    private String title;
    private String description;
    private String docPath;
    private ProjectStatus status;

    private List<ProjectColumnEntity> projectColumns;

    @Builder(builderMethodName = "childBuilder")
    public ProjectEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String docPath, ProjectStatus status, List<ProjectColumnEntity> projectColumns) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.docPath = docPath;
        this.status = status;
        this.projectColumns = projectColumns;
    }


}
