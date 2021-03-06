package domains.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
@Table
public class ProjectColumnEntity {
    @Id
    private Long id;
    private String name;
    private String code;
    private Long project_id;
}
