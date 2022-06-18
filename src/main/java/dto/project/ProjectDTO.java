package dto.project;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;


/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 3:05 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {

    private Long id;
    private String title;
    private String description;

    @SerializedName("doc_path")
    private String docPath;
    private String status;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("created_by")
    private Long createdBy;


}
