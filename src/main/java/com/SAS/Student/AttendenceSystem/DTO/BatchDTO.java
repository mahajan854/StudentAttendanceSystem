package com.SAS.Student.AttendenceSystem.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class BatchDTO {
    private int id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long trainerId;
    private Long subjectId;

    public BatchDTO() {
    	
    }

	public BatchDTO(int id, String name, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate,
			Long trainerId, Long subjectId) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainerId = trainerId;
		this.subjectId = subjectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "BatchDTO [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", trainerId=" + trainerId + ", subjectId="
				+ subjectId + ", getId()=" + getId() + ", getName()=" + getName() + ", getStartTime()=" + getStartTime()
				+ ", getEndTime()=" + getEndTime() + ", getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + ", getTrainerId()=" + getTrainerId() + ", getSubjectId()=" + getSubjectId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
}
