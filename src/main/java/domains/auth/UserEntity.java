package domains.auth;

import domains.Auditable;
import enums.Language;
import enums.UserRole;
import enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 9:27 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */

@Table(name = "users")
public final class UserEntity extends Auditable {
    private String username;
    private String password;
    private UserStatus status;
    private UserRole role;
    private Language language;
@Builder(builderMethodName = "childBuilder")
    public UserEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, int deleted, String username, String password, UserStatus status, UserRole role, Language language) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.language = language;
    }


}