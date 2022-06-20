package dto.auth;


import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @SerializedName("userName")
    private String username;
    private String password;
    @SerializedName("employee")
    private EmployeeCreateDTO employeeCreateDTO;
    private String language;

}
