package dto.auth;

import dto.auth.EmployeeDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String status;
    private String role;
    private String language;
    private EmployeeDTO employee;
}
