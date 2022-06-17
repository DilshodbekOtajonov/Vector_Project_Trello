package dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private String password;
    private String localPassword;
    private String phone_number;
}
