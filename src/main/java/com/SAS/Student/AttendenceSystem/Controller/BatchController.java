package com.SAS.Student.AttendenceSystem.Controller;

import com.SAS.Student.AttendenceSystem.Entity.Batch;
import com.SAS.Student.AttendenceSystem.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    // Get all batches
    @GetMapping
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    // Get batch by ID
    @GetMapping("/{id}")
    public Batch getBatchById(@PathVariable int id) {
        return batchService.getBatchById(id);
    }

    // Add new batch
    @PostMapping
    public Batch addBatch(@RequestBody Batch batch) {
        return batchService.saveBatch(batch);
    }

    // Update batch
    @PutMapping("/{id}")
    public Batch updateBatch(@PathVariable int id, @RequestBody Batch batch) {
        return batchService.updateBatch(id, batch);
    }

    // Delete batch
    @DeleteMapping("/{id}")
    public void deleteBatch(@PathVariable int id) {
        batchService.deleteBatch(id);
    }
}
