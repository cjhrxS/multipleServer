package ua.ita.smartcarservice.service.impl.alerts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;
import ua.ita.smartcarservice.repository.alerts.NotificationsRepository;
import ua.ita.smartcarservice.service.alerts.NotificationService;

@Service
public class NotificationsServiceImpl implements NotificationService{

	@Autowired
	NotificationsRepository notificationsRepository;

	@Override
	public void deleteNotificationById(Long id) {
		notificationsRepository.deleteById(id);
	}

	@Override
	public List<NotificationsDto> getAllNotificationsForUser(Long userId) {
		List<NotificationsDto> dtos = new ArrayList<>();
		for (Notifications entity : notificationsRepository.getAllNotificationsForUser(userId)) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}
	
	@Override
	public void saveNotification(NotificationsDto notificationDto) {
		notificationsRepository.save(dtoToEntity(notificationDto));
	}

	@Override
	public void saveAllNotifications(List<NotificationsDto> notificationsList) {
		List<Notifications> entities = new ArrayList<>();
		for (NotificationsDto dto : notificationsList) {
			entities.add(dtoToEntity(dto));
		}
		notificationsRepository.saveAll(entities);
	}
	
	private Notifications dtoToEntity(NotificationsDto dto) {
		return new Notifications(dto.getMessage(), 
								 dto.getNotificationTime(), 
								 dto.getCarId(), 
								 dto.getUserId(), 
								 dto.getSkillId()) ;
	}
	
	private NotificationsDto entityToDto (Notifications entity) {
		return new NotificationsDto(entity.getId(),
									entity.getMessage(),
									entity.getNotificationTime(),
									entity.getCarId(), 
									entity.getUserId(), 
									entity.getSkillId());
	}
}
