package domains.project;

import jakarta.persistence.Table;
import lombok.*;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 10:58 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "project_column")
public class ProjectColumnEntity {
    private Long id;
    private String name;
    private String code;
    private Long project_id;
}
