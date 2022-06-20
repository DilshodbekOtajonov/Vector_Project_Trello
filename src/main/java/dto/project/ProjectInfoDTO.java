package dto.project;

import com.google.gson.annotations.SerializedName;
import enums.ProjectStatus;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ProjectInfoDTO {
    @Id
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("doc_path")
    private String docPath;

    private ProjectStatus status;
    @SerializedName("created_by")
    private Long createdBy;
}
