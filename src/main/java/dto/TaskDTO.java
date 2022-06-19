package dto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<CommentDTO> commentDTOS=new ArrayList<>();

}
