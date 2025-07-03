package com.SAS.Student.AttendenceSystem.Service;

import com.SAS.Student.AttendenceSystem.DTO.AbsentNotificationDTO;

import java.util.List;

public interface AbsentNotificationService {
    AbsentNotificationDTO createAbsentNotification(AbsentNotificationDTO dto);
    List<AbsentNotificationDTO> getAllAbsentNotifications();
    List<AbsentNotificationDTO> getNotificationsByStudentId(Long studentId);
    void checkAndGenerateNotification(Long studentId);
    List<AbsentNotificationDTO> getNotificationsByMonthAndYear(int month, int year);
    void checkAndNotifyCoordinator();
}
