package com.SAS.Student.AttendenceSystem.Service;

import com.SAS.Student.AttendenceSystem.DTO.StudentBatchesDTO;

import java.util.List;

public interface StudentBatchesService {
    StudentBatchesDTO save(StudentBatchesDTO dto);
    StudentBatchesDTO update(int id, StudentBatchesDTO dto);
    void delete(int id);
    List<StudentBatchesDTO> getAll();
    StudentBatchesDTO getById(int id);
}
