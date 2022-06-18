package dto;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 4:52 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class TaskDTO {

    private Long id;
    private String level;
    private Integer order;
    private String title;
    private String description;
    private String priority;

    @SerializedName("created_at")
    private Timestamp createdAt;
    @SerializedName("project_id")
    private Long projectId;
    @SerializedName("project_column_id")
    private Long projectColumnId;


}
