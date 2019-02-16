package ua.ita.smartcarservice.entity.alerts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table (name = "notifications")
public class Notifications {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length = 1050, name = "message", nullable = false)
	private String message;
	
	@Column (name = "notification_time", columnDefinition = "DATETIME")
	@Temporal (TemporalType.TIMESTAMP)
	private Date notificationTime;
	
	@Column (name = "car_id")
	private Long carId;
	
	@Column (name = "user_id")
	private Long userId;
	
	@Column (name = "skill_id")
	private Long skillId;

	public Notifications(String message, Date notificationTime, Long carId, Long userId, Long skillId) {
		this.message = message;
		this.notificationTime = notificationTime;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}
}
