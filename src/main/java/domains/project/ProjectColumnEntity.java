package domains.project;

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
public class ProjectColumnEntity {
    private Long id;
    private String name;
    private String code;
    private Long project_id;
}
