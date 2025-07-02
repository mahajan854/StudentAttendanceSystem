package com.SAS.Student.AttendenceSystem.Service;

import com.SAS.Student.AttendenceSystem.DTO.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    void updateStudentStatus(Long studentId, int totalClasses, int absentClasses);
}