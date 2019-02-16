package ua.ita.smartcarservice.dto.alerts;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationsDto {

	private Long id;
	private String message;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
	private Date notificationTime;
	private Long carId;
	private Long userId;
	private Long skillId;
	
	//to save Notification
	public NotificationsDto(String message, Date notificationTime, Long carId, Long userId, Long skillId) {
		this.message = message;
		this.notificationTime = notificationTime;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}

	public NotificationsDto(Long id, String message, Date notificationTime, Long carId, Long userId, Long skillId) {
		this.id = id;
		this.message = message;
		this.notificationTime = notificationTime;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "NotificationsDto [id=" + id + ", message=" + message + ", notificationTime=" + notificationTime
				+ ", carId=" + carId + ", userId=" + userId + ", skillId=" + skillId + "]";
	}
}
