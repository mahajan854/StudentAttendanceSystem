package com.SAS.Student.AttendenceSystem.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDTO {
    private int id;
    private LocalDate date;
    private String status;
    private Long studentId;
    private Integer batchId;
    private Long coordinatorId;
    private Integer trainerId;
    private LocalDateTime markedAt;

    public AttendanceDTO() {
    	
    }

	public AttendanceDTO(int id, LocalDate date, String status, Long studentId, int batchId, Long coordinatorId,
			Integer trainerId, LocalDateTime markedAt) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.studentId = studentId;
		this.batchId = batchId;
		this.coordinatorId = coordinatorId;
		this.trainerId = trainerId;
		this.markedAt = markedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(int i) {
		this.batchId = i;
	}

	public Long getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(Long coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	public Integer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Integer integer) {
		this.trainerId = integer;
	}

	public LocalDateTime getMarkedAt() {
		return markedAt;
	}

	public void setMarkedAt(LocalDateTime markedAt) {
		this.markedAt = markedAt;
	}

	@Override
	public String toString() {
		return "AttendanceDTO [id=" + id + ", date=" + date + ", status=" + status + ", studentId=" + studentId
				+ ", batchId=" + batchId + ", coordinatorId=" + coordinatorId + ", trainerId=" + trainerId
				+ ", markedAt=" + markedAt + ", getId()=" + getId() + ", getDate()=" + getDate() + ", getStatus()="
				+ getStatus() + ", getStudentId()=" + getStudentId() + ", getBatchId()=" + getBatchId()
				+ ", getCoordinatorId()=" + getCoordinatorId() + ", getTrainerId()=" + getTrainerId()
				+ ", getMarkedAt()=" + getMarkedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
}
