package dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String level;
    private String priority;
    @SerializedName("project_column_id")
    private Long projectColumnId;
    private Long order;
    @SerializedName("created_by")
    private Long createdBy;
}
