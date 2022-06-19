package domains.task;


import com.google.gson.annotations.SerializedName;
import domains.Auditable;
import domains.project.ProjectColumnEntity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "task")
@Getter
@Setter
public class TaskEntity extends Auditable {

    private String title;
    private String description;
    @SerializedName("level")
    private String taskLevel;
    private String priority;

    @SerializedName("project_column_id")
    private ProjectColumnEntity projectColumnId;
    private Long order;

    @Builder(builderMethodName = "childBuilder")
    public TaskEntity(Long id, Timestamp createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String taskLevel, String priorty, ProjectColumnEntity projectColumnId, Long order) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.taskLevel = taskLevel;
        this.priority = priorty;
        this.projectColumnId = projectColumnId;
        this.order = order;
    }
}
