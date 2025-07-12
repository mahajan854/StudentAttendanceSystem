package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.DTO.StudentBatchesDTO;
import com.SAS.Student.AttendenceSystem.Entity.Batch;
import com.SAS.Student.AttendenceSystem.Entity.Student;
import com.SAS.Student.AttendenceSystem.Entity.StudentBatches;
import com.SAS.Student.AttendenceSystem.Repository.BatchRepository;
import com.SAS.Student.AttendenceSystem.Repository.StudentRepository;
import com.SAS.Student.AttendenceSystem.Repository.StudentsBatchesRepository;
import com.SAS.Student.AttendenceSystem.Service.StudentBatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentBatchImpl implements StudentBatchesService {

    @Autowired
    private StudentsBatchesRepository studentBatchesRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public StudentBatchesDTO save(StudentBatchesDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Batch batch = batchRepository.findById(dto.getBatchId().intValue())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        StudentBatches entity = new StudentBatches();
        entity.setStudent(student);
        entity.setBatch(batch);

        StudentBatches saved = studentBatchesRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public StudentBatchesDTO update(int id, StudentBatchesDTO dto) {
        StudentBatches entity = studentBatchesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentBatch not found"));
        entity.setStudent(studentRepository.findById(dto.getStudentId()).orElseThrow());
        entity.setBatch(batchRepository.findById(dto.getBatchId().intValue()).orElseThrow());
        return convertToDTO(studentBatchesRepository.save(entity));
    }

    @Override
    public void delete(int id) {
        studentBatchesRepository.deleteById(id);
    }

    @Override
    public List<StudentBatchesDTO> getAll() {
        return studentBatchesRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentBatchesDTO getById(int id) {
        StudentBatches entity = studentBatchesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentBatch not found"));
        return convertToDTO(entity);
    }

    private StudentBatchesDTO convertToDTO(StudentBatches entity) {
        return new StudentBatchesDTO(
                entity.getId(),
                entity.getStudent().getId(),
                (long) entity.getBatch().getId()
        );
    }
}
