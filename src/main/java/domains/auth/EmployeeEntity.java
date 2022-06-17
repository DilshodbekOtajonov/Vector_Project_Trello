package domains.auth;

import jakarta.persistence.Table;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 9:28 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */

@Table(name = "employee")
public class EmployeeEntity {
    private long userId;
    private String fullName;
    private String phoneNumber;
    private String email;
}
