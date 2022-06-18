package dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 11:53 AM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginDTO {
    private String username;
    private String password;
}


