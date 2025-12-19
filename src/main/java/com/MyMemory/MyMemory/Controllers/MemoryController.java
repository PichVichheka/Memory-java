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

    // Create a new memory
    @PostMapping
    public Memory createMemory(@RequestBody Memory memory) {
        return memoryService.createMemory(memory.getTitle(), memory.getDescription());
    }

    // Read all memories
    @GetMapping
    public List<Memory> getAllMemories() {
        return memoryService.getAllMemories();
    }

    // Read memory by id
    @GetMapping("/{id}")
    public Memory getMemoryById(@PathVariable Long id) {
        return memoryService.getMemoryById(id); // will throw ResourceNotFoundException if not found
    }

    // Update memory
    @PutMapping("/{id}")
    public Memory updateMemory(@PathVariable Long id, @RequestBody Memory memory) {
        return memoryService.updateMemory(id, memory.getTitle(), memory.getDescription());
    }

    // Delete memory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }

    // Fuzzy search by title or category
   
    @GetMapping("/memories/search")
    public ResponseEntity<List<Memory>> searchMemories(@RequestParam String keyword) {
        List<Memory> results = memoryService.searchMemories(keyword);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 if nothing found
        }
        return ResponseEntity.ok(results); // HTTP 200 with body
    }
}

