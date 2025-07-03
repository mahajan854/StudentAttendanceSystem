package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.DTO.AbsentNotificationDTO;
import com.SAS.Student.AttendenceSystem.Entity.*;
import com.SAS.Student.AttendenceSystem.Repository.*;
import com.SAS.Student.AttendenceSystem.Service.AbsentNotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbsentNotificationServiceImpl implements AbsentNotificationService {

    @Autowired
    private AbsentNotificationRepository absentNotificationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentsBatchesRepository studentBatchesRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public AbsentNotificationDTO createAbsentNotification(AbsentNotificationDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<StudentBatches> studentBatches = studentBatchesRepository.findByStudent(student);
        if (studentBatches.isEmpty()) {
            throw new RuntimeException("Student is not assigned to any batch");
        }

        Batch batch = studentBatches.get(0).getBatch();
        Trainer trainer = batch.getTrainer();

        Login login = loginRepository.findByTrainer(trainer);
        Coordinator coordinator = login != null ? login.getCoordinator() : null;

        AbsentNotification notification = new AbsentNotification();
        notification.setDateSent(LocalDate.now());
        notification.setMethod(dto.getMethod());
        notification.setReason(dto.getReason());
        notification.setStudent(student);

        AbsentNotification saved = absentNotificationRepository.save(notification);

        if (coordinator != null) {
            sendEmailToCoordinator(coordinator.getEmail(), student.getName(), dto.getReason());
        }

        return new AbsentNotificationDTO(
                saved.getId(),
                saved.getDateSent(),
                saved.getMethod(),
                saved.getReason(),
                saved.getStudent().getId()
        );
    }

    @Override
    public List<AbsentNotificationDTO> getAllAbsentNotifications() {
        return absentNotificationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsentNotificationDTO> getNotificationsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return absentNotificationRepository.findByStudent(student).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void checkAndGenerateNotification(Long studentId) {
        System.out.println("🟡 Checking student ID: " + studentId);
        List<Attendance> records = attendanceRepository.findByStudentId(studentId);

        long absentCount = records.stream()
                .filter(r -> r.getStatus() == Attendance.Status.Absent)
                .count();

        System.out.println("🔴 Absent count for student " + studentId + " = " + absentCount);

        if (absentCount > 3) {
            System.out.println("✅ Triggering absent notification for student " + studentId);
            AbsentNotificationDTO dto = new AbsentNotificationDTO();
            dto.setStudentId(studentId);
            dto.setMethod("Email");
            dto.setReason("Absent more than 3 days");
            createAbsentNotification(dto);
        } else {
            System.out.println("ℹ️ Student " + studentId + " has not crossed absence threshold.");
        }
    }

    @Override
    public List<AbsentNotificationDTO> getNotificationsByMonthAndYear(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return absentNotificationRepository.findByDateSentBetween(start, end).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void checkAllStudentsAndNotify() {
        List<Student> absentStudents = attendanceRepository.findStudentsWithMoreThan3Absents();

        Map<Coordinator, List<Student>> groupedByCoordinator = new HashMap<>();

        for (Student student : absentStudents) {
            List<StudentBatches> batches = studentBatchesRepository.findByStudent(student);
            if (batches.isEmpty()) continue;

            Trainer trainer = batches.get(0).getBatch().getTrainer();
            Login login = loginRepository.findByTrainer(trainer);
            Coordinator coordinator = login != null ? login.getCoordinator() : null;

            if (coordinator != null) {
                groupedByCoordinator.computeIfAbsent(coordinator, k -> new ArrayList<>()).add(student);
            }
        }

        for (Map.Entry<Coordinator, List<Student>> entry : groupedByCoordinator.entrySet()) {
            Coordinator coordinator = entry.getKey();
            List<Student> students = entry.getValue();
            sendGroupEmailToCoordinator(coordinator.getEmail(), students, "Absent more than 3 days");
        }
    }

    private void sendEmailToCoordinator(String toEmail, String studentName, String reason) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Student Absent Notification");
            message.setText("Student " + studentName + " has been absent. Reason: " + reason);
            mailSender.send(message);

            System.out.println("🟢 Mail send method executed.");
            System.out.println("📧 Email sent to coordinator (" + toEmail + ") for student: " + studentName);
        } catch (Exception e) {
            System.out.println("❌ Failed to send email: " + e.getMessage());
        }
    }

    private void sendGroupEmailToCoordinator(String toEmail, List<Student> students, String reason) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Absent Students Notification");

            StringBuilder body = new StringBuilder("The following students have been marked absent more than 3 times:\n\n");
            for (Student student : students) {
                body.append("- ").append(student.getName()).append("\n");
            }
            body.append("\nReason: ").append(reason);

            message.setText(body.toString());
            mailSender.send(message);

            System.out.println("📧 Group email sent to coordinator (" + toEmail + ") for multiple students.");
        } catch (Exception e) {
            System.out.println("❌ Failed to send group email: " + e.getMessage());
        }
    }

    private AbsentNotificationDTO mapToDTO(AbsentNotification n) {
        return new AbsentNotificationDTO(
                n.getId(),
                n.getDateSent(),
                n.getMethod(),
                n.getReason(),
                n.getStudent().getId()
        );
    }

    @Override
    public void checkAndNotifyCoordinator() {
        List<Student> students = attendanceRepository.findStudentsWithMoreThan3Absents();
        if (!students.isEmpty()) {
            String coordinatorEmail = "coordinator@example.com";
            sendGroupEmailToCoordinator(coordinatorEmail, students, "Absent more than 3 times");
        }
    }
}
