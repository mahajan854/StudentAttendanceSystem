package com.SAS.Student.AttendenceSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can add custom queries here if needed.
    Student findByEmail(String email);
   
}
