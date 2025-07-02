package com.SAS.Student.AttendenceSystem.Service.impl;

import com.SAS.Student.AttendenceSystem.Entity.Batch;
import com.SAS.Student.AttendenceSystem.Repository.BatchRepository;
import com.SAS.Student.AttendenceSystem.Service.BatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Batch> optionalBatch = batchRepository.findById(id);
        if (optionalBatch.isPresent()) {
            Batch existingBatch = optionalBatch.get();
            existingBatch.setName(updatedBatch.getName());
            existingBatch.setStartTime(updatedBatch.getStartTime());
            existingBatch.setEndTime(updatedBatch.getEndTime());
            existingBatch.setStartDate(updatedBatch.getStartDate());
            existingBatch.setEndDate(updatedBatch.getEndDate());
            existingBatch.setTrainer(updatedBatch.getTrainer());
            existingBatch.setSubject(updatedBatch.getSubject());
            return batchRepository.save(existingBatch);
        }
        return null;
    }

    @Override
    public void deleteBatch(int id) {
        batchRepository.deleteById(id);
    }

    @Override
    public Batch getBatchById(int id) {
        return batchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }
}
