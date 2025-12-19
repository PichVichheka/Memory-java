package com.MyMemory.MyMemory.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    // Create memory
    @PostMapping
    public Memory createMemory(@RequestBody Memory memory) {
        return memoryService.createMemory(
            memory.getTitle(),
            memory.getDescription(),
            memory.getCategory()
        );
    }

    // Read all memories
    @GetMapping
    public List<Memory> getAllMemories() {
        return memoryService.getAllMemories();
    }

    // Read memory by id
    @GetMapping("/{id}")
    public ResponseEntity<Memory> getMemoryById(@PathVariable Long id) {
        Memory memory = memoryService.getMemoryById(id);
        return ResponseEntity.ok(memory);
    }

    // Update memory
    @PutMapping("/{id}")
    public ResponseEntity<Memory> updateMemory(@PathVariable Long id, @RequestBody Memory memory) {
        Memory updated = memoryService.updateMemory(
            id,
            memory.getTitle(),
            memory.getDescription(),
            memory.getCategory()
        );
        return ResponseEntity.ok(updated);
    }

    // Delete memory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }

    // Fuzzy search memories by title or category
    @GetMapping("/search")
    public List<Memory> searchMemories(@RequestParam String keyword) {
        return memoryService.searchMemories(keyword);
    }
}
