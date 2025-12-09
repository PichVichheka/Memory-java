package com.MyMemory.MyMemory.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.MyMemory.MyMemory.Enitity.Memory;
import com.MyMemory.MyMemory.Services.MemoryService;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @PostMapping
    public Memory createMemory(@RequestBody Memory memory) {
        return memoryService.createMemory(
            memory.getTitle(),
            memory.getDescription()
        );
    }

    // Read all
    @GetMapping
    public List<Memory> getAllMemories() {
        return memoryService.getAllMemories();
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<Memory> getMemoryById(@PathVariable Long id) {
        return memoryService.getMemoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Memory> updateMemory(@PathVariable Long id, @RequestBody Memory memory) {
        Memory updated = memoryService.updateMemory(id, memory.getTitle(), memory.getDescription());
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }
}

