package com.SAS.Student.AttendenceSystem.Controller;

import com.SAS.Student.AttendenceSystem.DTO.StudentBatchesDTO;
import com.SAS.Student.AttendenceSystem.Service.StudentBatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-batches")
@CrossOrigin(origins = "*")
public class StudentBatchesController {

    @Autowired
    private StudentBatchesService studentBatchesService;

    // ✅ Create new student-batch mapping
    @PostMapping
    public StudentBatchesDTO create(@RequestBody StudentBatchesDTO dto) {
        return studentBatchesService.save(dto);
    }

    // ✅ Update existing mapping by ID
    @PutMapping("/{id}")
    public StudentBatchesDTO update(@PathVariable int id, @RequestBody StudentBatchesDTO dto) {
        return studentBatchesService.update(id, dto);
    }

    // ✅ Delete mapping by ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentBatchesService.delete(id);
    }

    // ✅ Get all mappings
    @GetMapping
    public List<StudentBatchesDTO> getAll() {
        return studentBatchesService.getAll();
    }

    // ✅ Get mapping by ID
    @GetMapping("/{id}")
    public StudentBatchesDTO getById(@PathVariable int id) {
        return studentBatchesService.getById(id);
    }
}
