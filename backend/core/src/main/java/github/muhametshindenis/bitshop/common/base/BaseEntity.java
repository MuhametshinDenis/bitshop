package github.muhametshindenis.bitshop.common.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 17 August 2025
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BaseEntity {
	@Column(nullable = false, updatable = false, name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		initDefaults();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	protected void initDefaults() {}
}
