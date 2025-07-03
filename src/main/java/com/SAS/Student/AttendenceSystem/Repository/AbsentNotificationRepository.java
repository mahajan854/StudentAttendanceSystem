package com.SAS.Student.AttendenceSystem.Repository;

import com.SAS.Student.AttendenceSystem.Entity.AbsentNotification;
import com.SAS.Student.AttendenceSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsentNotificationRepository extends JpaRepository<AbsentNotification, Integer> {
    List<AbsentNotification> findByStudent(Student student);
    List<AbsentNotification> findByDateSentBetween(LocalDate startDate, LocalDate endDate);
}
