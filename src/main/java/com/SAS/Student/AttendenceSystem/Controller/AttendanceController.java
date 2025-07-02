package com.SAS.Student.AttendenceSystem.Controller;

import com.SAS.Student.AttendenceSystem.DTO.AttendanceDTO;
import com.SAS.Student.AttendenceSystem.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public AttendanceDTO markAttendance(@RequestParam Long studentId, @RequestBody AttendanceDTO dto) {
        return attendanceService.markAttendance(studentId, dto);
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceDTO> getAttendancesByStudent(@PathVariable Long studentId) {
        return attendanceService.getAttendancesByStudent(studentId);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable int id) {
        attendanceService.deleteAttendance(id);
    }
}