package com.SAS.Student.AttendenceSystem.Controller;

import com.SAS.Student.AttendenceSystem.DTO.AbsentNotificationDTO;
import com.SAS.Student.AttendenceSystem.Service.AbsentNotificationService;
import com.SAS.Student.AttendenceSystem.Service.impl.AbsentNotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class AbsentNotificationController {

    @Autowired
    private AbsentNotificationService notificationService;

    @Autowired
    private AbsentNotificationServiceImpl notificationServiceImpl;

    // ✅ Get all absent notifications
    @GetMapping
    public List<AbsentNotificationDTO> getAllNotifications() {
        return notificationService.getAllAbsentNotifications();
    }

    // ✅ Get notifications for a specific student
    @GetMapping("/student/{studentId}")
    public List<AbsentNotificationDTO> getNotificationsByStudentId(@PathVariable Long studentId) {
        return notificationService.getNotificationsByStudentId(studentId);
    }

    // ✅ Trigger notification generation for a specific student
    @PostMapping("/check/{studentId}")
    public void checkAndGenerateNotification(@PathVariable Long studentId) {
        notificationService.checkAndGenerateNotification(studentId);
    }

    // ✅ Trigger notifications for multiple students
    @PostMapping("/checkMultiple")
    public void checkAndGenerateForMultipleStudents(@RequestBody List<Long> studentIds) {
        for (Long studentId : studentIds) {
            notificationService.checkAndGenerateNotification(studentId);
        }
    }

    // ✅ Filter notifications by month and year
    @GetMapping("/filter")
    public List<AbsentNotificationDTO> getNotificationsByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return notificationService.getNotificationsByMonthAndYear(month, year);
    }

    // ✅ Send group email to coordinators if students are absent more than 3 times
    @PostMapping("/send-group-emails")
    public String sendGroupAbsentEmails() {
        notificationServiceImpl.checkAllStudentsAndNotify();
        return "Group email notifications sent for students with >3 absents.";
    }
}
