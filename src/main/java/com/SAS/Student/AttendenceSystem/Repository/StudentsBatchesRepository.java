package com.SAS.Student.AttendenceSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Student;
import com.SAS.Student.AttendenceSystem.Entity.StudentBatches;

@Repository
public interface StudentsBatchesRepository extends JpaRepository<StudentBatches, Integer> {
    // Custom query to find by student and batch relationship
    StudentBatches findByStudentIdAndBatchId(int studentId, int batchId);
    List<StudentBatches> findByStudent(Student student);
}