package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.DTO.StudentDTO;
import com.SAS.Student.AttendenceSystem.Entity.Student;
import com.SAS.Student.AttendenceSystem.Repository.StudentRepository;
import com.SAS.Student.AttendenceSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getPhone(), s.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Student not found"));
        return new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getPhone(), s.getStatus());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Student not found"));
        s.setName(studentDTO.getName());
        s.setEmail(studentDTO.getEmail());
        s.setPhone(studentDTO.getPhone());
        s.setStatus(studentDTO.getStatus());
        studentRepository.save(s);
        return new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getPhone(), s.getStatus());
    }

    @Override
    public void deleteStudent(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Student not found"));
        studentRepository.delete(s);
    }

    @Override
    public void updateStudentStatus(Long studentId, int totalClasses, int absentClasses) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Student not found"));
        double percentage = ((double) absentClasses / totalClasses) * 100;
        student.setStatus(percentage > 50 ? Student.Status.inactive : Student.Status.active);
        studentRepository.save(student);
    }
}