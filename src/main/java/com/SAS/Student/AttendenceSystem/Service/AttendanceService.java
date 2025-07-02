package com.SAS.Student.AttendenceSystem.Service;

import com.SAS.Student.AttendenceSystem.DTO.AttendanceDTO;
import java.util.List;

public interface AttendanceService {
    AttendanceDTO markAttendance(Long studentId, AttendanceDTO dto);
    List<AttendanceDTO> getAttendancesByStudent(Long studentId);
    void deleteAttendance(int id);
}