package com.SAS.Student.AttendenceSystem.DTO;

public class StudentBatchesDTO {
    private int id;
    private Long studentId;
    private Long batchId;

    public StudentBatchesDTO() {}

    public StudentBatchesDTO(int id, Long studentId, Long batchId) {
        this.id = id;
        this.studentId = studentId;
        this.batchId = batchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }
}
