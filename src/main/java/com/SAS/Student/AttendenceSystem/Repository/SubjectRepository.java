package com.SAS.Student.AttendenceSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    // Custom queries can be added here if needed
    Subject findByName(String name);
}
