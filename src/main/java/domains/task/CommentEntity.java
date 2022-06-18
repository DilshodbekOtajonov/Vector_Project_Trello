package domains.task;


import domains.Auditable;
import domains.task.TaskEntity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "comment")
public class CommentEntity extends Auditable {


    private String message;
    private TaskEntity taskId;

    public CommentEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, int deleted, String message, TaskEntity taskId) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.message = message;
        this.taskId = taskId;
    }
}
