package com.SAS.Student.AttendenceSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Login;
import com.SAS.Student.AttendenceSystem.Entity.Trainer;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByEmailAndPassword(String email, String password);
    Login findByTrainer(Trainer trainer);
}