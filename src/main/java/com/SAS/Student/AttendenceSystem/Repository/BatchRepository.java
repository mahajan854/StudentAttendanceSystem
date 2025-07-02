package com.SAS.Student.AttendenceSystem.Repository;

import com.SAS.Student.AttendenceSystem.Entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
    Batch findByName(String name);
}
