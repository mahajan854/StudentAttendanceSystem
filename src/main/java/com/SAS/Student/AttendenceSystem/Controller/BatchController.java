package com.SAS.Student.AttendenceSystem.Controller;

import com.SAS.Student.AttendenceSystem.Entity.Batch;
import com.SAS.Student.AttendenceSystem.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
@CrossOrigin(origins = "*")
public class BatchController {

    @Autowired
    private BatchService batchService;

    // Create
    @PostMapping
    public Batch createBatch(@RequestBody Batch batch) {
        return batchService.saveBatch(batch);
    }

    // Read all
    @GetMapping
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    // Read one
    @GetMapping("/{id}")
    public Batch getBatchById(@PathVariable int id) {
        return batchService.getBatchById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Batch updateBatch(@PathVariable int id, @RequestBody Batch batch) {
        return batchService.updateBatch(id, batch);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteBatch(@PathVariable int id) {
        batchService.deleteBatch(id);
        return "Batch deleted with id: " + id;
    }
}
