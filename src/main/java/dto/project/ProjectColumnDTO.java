package dto.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 3:27 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnDTO {
    private Long id;
    private Long project_id;
    private String name;
    private String code;
    private Long order;
}
