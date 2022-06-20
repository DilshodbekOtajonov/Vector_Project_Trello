package dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/20/22 6:11 PM (Monday)
 * VectorGroupProject/IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TaskMemberCreateDTO {
    private String email;
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("task_id")
    private Long taskId;
}


