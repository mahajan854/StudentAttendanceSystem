package com.SAS.Student.AttendenceSystem.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Attendance;
import com.SAS.Student.AttendenceSystem.Entity.Student;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    // ✅ Existing: Find all attendance records by student ID
    List<Attendance> findByStudentId(Long studentId);

    // ✅ Existing: Find attendance by student ID, batch ID, and date
    Attendance findByStudentIdAndBatchIdAndDate(int studentId, int batchId, LocalDate date);

    // ✅ NEW: Count how many times a student was absent
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = :studentId AND a.status = 'Absent'")
    int countAbsentsByStudent(@Param("studentId") int studentId);

    // ✅ NEW: Get students who are absent more than 3 times
    @Query("SELECT a.student FROM Attendance a WHERE a.status = 'Absent' GROUP BY a.student HAVING COUNT(a) > 3")
    List<Student> findStudentsWithMoreThan3Absents();
}
