package com.SAS.Student.AttendenceSystem.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.AbsentNotification;

@Repository
public interface AbsentNotificationRepository extends JpaRepository<AbsentNotification, Integer> {
    // Custom queries can be added here if needed
}