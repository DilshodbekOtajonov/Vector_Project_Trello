package dto.auth;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ProjectDTO {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("doc_path")
    private String docPath;
    @SerializedName("created_by")
    private Long createdBy;
}
