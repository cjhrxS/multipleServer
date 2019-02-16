package ua.ita.smartcarservice.service.alerts;

import java.util.List;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;

public interface NotificationService {
	
	void saveNotification(NotificationsDto notificationDto);

	void saveAllNotifications(List<NotificationsDto> notificationsList);
	
	List<NotificationsDto> getAllNotificationsForUser(Long userId);
	
	void deleteNotificationById (Long id);
}
