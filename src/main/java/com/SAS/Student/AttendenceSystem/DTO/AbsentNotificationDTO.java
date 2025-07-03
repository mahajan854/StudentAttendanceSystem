package com.SAS.Student.AttendenceSystem.DTO;

import java.time.LocalDate;

public class AbsentNotificationDTO {

    private int id;
    private LocalDate dateSent;
    private String method;
    private String reason;
    private Long studentId;

    // Default constructor
    public AbsentNotificationDTO() {}

    // Parameterized constructor
    public AbsentNotificationDTO(int id, LocalDate dateSent, String method, String reason, Long studentId) {
        this.id = id;
        this.dateSent = dateSent;
        this.method = method;
        this.reason = reason;
        this.studentId = studentId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    // toString method
    @Override
    public String toString() {
        return "AbsentNotificationDTO{" +
                "id=" + id +
                ", dateSent=" + dateSent +
                ", method='" + method + '\'' +
                ", reason='" + reason + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
