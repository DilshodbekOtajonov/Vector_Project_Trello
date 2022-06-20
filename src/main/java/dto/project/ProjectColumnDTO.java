package dto.project;

import com.google.gson.annotations.SerializedName;
import dto.TaskDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 3:27 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectColumnDTO {
    private Long id;
    private Long project_id;
    private String name;
    private String code;
    private Long order;
    @SerializedName("tasks")
    private List<TaskDTO> tasks;
}
