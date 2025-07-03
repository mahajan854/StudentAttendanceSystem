package com.SAS.Student.AttendenceSystem.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AbsentNotifications")
public class AbsentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateSent;
    private String method;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Default constructor
    public AbsentNotification() {}

    // Parameterized constructor
    public AbsentNotification(int id, LocalDate dateSent, String method, String reason, Student student) {
        this.id = id;
        this.dateSent = dateSent;
        this.method = method;
        this.reason = reason;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // toString method
    @Override
    public String toString() {
        return "AbsentNotification{" +
                "id=" + id +
                ", dateSent=" + dateSent +
                ", method='" + method + '\'' +
                ", reason='" + reason + '\'' +
                ", student=" + (student != null ? student.getId() : null) +
                '}';
    }
}
