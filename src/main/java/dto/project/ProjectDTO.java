package dto.project;

import com.google.gson.annotations.SerializedName;
import dto.project.ProjectColumnDTO;
import enums.ProjectStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
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
public class ProjectDTO {
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<ProjectColumnDTO> projectColumns = new ArrayList<>();
}
