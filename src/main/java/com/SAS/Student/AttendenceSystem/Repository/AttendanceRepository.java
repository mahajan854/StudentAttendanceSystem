package com.SAS.Student.AttendenceSystem.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    
	//find student
	List<Attendance> findByStudentId(Long studentId); 

	
	//find specifica batch and student
    Attendance findByStudentIdAndBatchIdAndDate(int studentId, int batchId, LocalDate date);
    
}