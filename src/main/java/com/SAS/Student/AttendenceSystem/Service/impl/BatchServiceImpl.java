package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.Entity.Batch;
import com.SAS.Student.AttendenceSystem.Repository.BatchRepository;
import com.SAS.Student.AttendenceSystem.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public Batch updateBatch(int id, Batch updatedBatch) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + id));

        batch.setName(updatedBatch.getName());
        batch.setStartTime(updatedBatch.getStartTime());
        batch.setEndTime(updatedBatch.getEndTime());
        batch.setStartDate(updatedBatch.getStartDate());
        batch.setEndDate(updatedBatch.getEndDate());
        batch.setTrainer(updatedBatch.getTrainer());
        batch.setSubject(updatedBatch.getSubject());

        return batchRepository.save(batch);
    }

    @Override
    public void deleteBatch(int id) {
        batchRepository.deleteById(id);
    }

    @Override
    public Batch getBatchById(int id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + id));
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }
}
