package com.SAS.Student.AttendenceSystem.DTO;

import com.SAS.Student.AttendenceSystem.Entity.Student.Status;

public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Status status;

    // Constructor for creating a DTO from an Entity
    public StudentDTO(Long id, String name, String email, String phone, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    // Default Constructor (useful for Spring to deserialize JSON into DTO)
    public StudentDTO() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", status="
				+ status + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()=" + getEmail()
				+ ", getPhone()=" + getPhone() + ", getStatus()=" + getStatus() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
}
