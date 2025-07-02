package com.SAS.Student.AttendenceSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    // You can add custom queries here if needed.
    Trainer findByEmail(String email);
}
