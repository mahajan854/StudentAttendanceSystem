package com.SAS.Student.AttendenceSystem.DTO;

public class LoginDTO {
	private int id;
	private String email;
	private String password;
	private String role;
	private Long trainerId;
	private Long coordinatorId;

	public LoginDTO() {

	}

	public LoginDTO(int id, String email, String password, String role, Long trainerId, Long coordinatorId) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.trainerId = trainerId;
		this.coordinatorId = coordinatorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	public Long getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(Long coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", trainerId="
				+ trainerId + ", coordinatorId=" + coordinatorId + ", getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getRole()=" + getRole() + ", getTrainerId()=" + getTrainerId()
				+ ", getCoordinatorId()=" + getCoordinatorId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
