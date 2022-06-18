package dto;

import domains.project.ProjectColumnEntity;
import dto.ProjectColumnDTO;
import enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 3:05 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class ProjectDTO {
    @Id
    private Long id;
    private String title;
    private String description;

    @Column(name = "doc_path")
    private String docPath;
    private ProjectStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<ProjectColumnDTO> projectColumns=new ArrayList<>();
}
