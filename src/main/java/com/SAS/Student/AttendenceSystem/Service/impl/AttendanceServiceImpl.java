package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.DTO.AttendanceDTO;
import com.SAS.Student.AttendenceSystem.Entity.*;
import com.SAS.Student.AttendenceSystem.Repository.*;
import com.SAS.Student.AttendenceSystem.Service.AttendanceService;
import com.SAS.Student.AttendenceSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private CoordinatorRepository coordinatorRepository;

	@Autowired
	private StudentService studentService;

	@Override
	public AttendanceDTO markAttendance(Long studentId, AttendanceDTO dto) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Student not found"));

		Batch batch = batchRepository.findById(dto.getBatchId())
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Batch not found"));

		Trainer trainer = dto.getTrainerId() != null ? trainerRepository.findById(dto.getTrainerId())
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trainer not found")) : null;

		Coordinator coordinator = dto.getCoordinatorId() != null
				? coordinatorRepository.findById(dto.getCoordinatorId()).orElseThrow(
						() -> new ResponseStatusException(NOT_FOUND, "Coordinator not found"))
				: null;

		Attendance attendance = new Attendance();
		attendance.setDate(dto.getDate());
		attendance.setStatus(Attendance.Status.valueOf(dto.getStatus()));
		attendance.setStudent(student);
		attendance.setBatch(batch);
		attendance.setMarkedByTrainer(trainer);
		attendance.setMarkedByCoordinator(coordinator);
		attendance.setMarkedAt(LocalDateTime.now());

		Attendance saved = attendanceRepository.save(attendance);

		// Update student status after attendance is marked
		List<Attendance> allAttendances = attendanceRepository.findByStudentId(studentId);
		int total = allAttendances.size();
		int absent = (int) allAttendances.stream().filter(a -> a.getStatus() == Attendance.Status.Absent).count();
		studentService.updateStudentStatus(studentId, total, absent);

		AttendanceDTO result = new AttendanceDTO();
		result.setId(saved.getId());
		result.setDate(saved.getDate());
		result.setStatus(saved.getStatus().name());
		result.setStudentId(saved.getStudent().getId());
		result.setBatchId(saved.getBatch().getId());
		result.setTrainerId(saved.getMarkedByTrainer() != null ? saved.getMarkedByTrainer().getId() : null);
		result.setCoordinatorId(saved.getMarkedByCoordinator() != null ? saved.getMarkedByCoordinator().getId() : null);
		result.setMarkedAt(saved.getMarkedAt());
		return result;
	}

	@Override
	public List<AttendanceDTO> getAttendancesByStudent(Long studentId) {
		List<Attendance> attendances = attendanceRepository.findByStudentId(studentId);
		return attendances.stream().map(a -> {
			AttendanceDTO dto = new AttendanceDTO();
			dto.setId(a.getId());
			dto.setDate(a.getDate());
			dto.setStatus(a.getStatus().name());
			dto.setStudentId(a.getStudent().getId());
			dto.setBatchId(a.getBatch().getId());
			dto.setTrainerId(a.getMarkedByTrainer() != null ? a.getMarkedByTrainer().getId() : null);
			dto.setCoordinatorId(a.getMarkedByCoordinator() != null ? a.getMarkedByCoordinator().getId() : null);
			dto.setMarkedAt(a.getMarkedAt());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteAttendance(int id) {
		Attendance a = attendanceRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Attendance not found"));
		attendanceRepository.delete(a);
	}
}