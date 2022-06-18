package domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 9:57 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auditable {
    protected Long id;
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    protected Timestamp updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
    protected int deleted;
}
