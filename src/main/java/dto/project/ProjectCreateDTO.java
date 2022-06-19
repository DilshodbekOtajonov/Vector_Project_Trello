package dto.project;

import com.google.gson.annotations.SerializedName;
import enums.ProjectStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ProjectCreateDTO {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("doc_path")
    private String docPath;
    @SerializedName("created_by")
    private Long createdBy;
}
