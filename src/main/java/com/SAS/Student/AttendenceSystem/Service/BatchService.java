package com.SAS.Student.AttendenceSystem.Service;

import com.SAS.Student.AttendenceSystem.Entity.Batch;

import java.util.List;

public interface BatchService {
    Batch saveBatch(Batch batch);
    Batch updateBatch(int id, Batch batch);
    void deleteBatch(int id);
    Batch getBatchById(int id);
    List<Batch> getAllBatches();
	
}
