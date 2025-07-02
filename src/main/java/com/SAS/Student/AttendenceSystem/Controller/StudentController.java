package com.SAS.Student.AttendenceSystem.Controller;


import com.SAS.Student.AttendenceSystem.DTO.StudentDTO;
import com.SAS.Student.AttendenceSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint to fetch all students
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Endpoint to fetch a student by ID
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Endpoint to add a new student (if required)
    // Note: If you only add via SQL, you can skip this API, but it's here for completeness.
//    @PostMapping
//    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
//        return studentService.addStudent(studentDTO);
//    }

    // Endpoint to update student details
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    // Endpoint to delete a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

