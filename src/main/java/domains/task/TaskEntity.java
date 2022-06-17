package domains.task;


import domains.Auditable;
import domains.project.ProjectColumnEntity;
import domains.project.ProjectEntity;
import jakarta.persistence.Table;
import lombok.Builder;

import java.time.LocalDateTime;

@Table(name = "task")
public class TaskEntity extends Auditable {

    private String title;
    private String description;
    private String taskLevel;
    private String priorty;

    private ProjectColumnEntity projectColumnId;
    private Long order;

    public TaskEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String taskLevel, String priorty, ProjectColumnEntity projectColumnId, Long order) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.taskLevel = taskLevel;
        this.priorty = priorty;
        this.projectColumnId = projectColumnId;
        this.order = order;
    }
}
