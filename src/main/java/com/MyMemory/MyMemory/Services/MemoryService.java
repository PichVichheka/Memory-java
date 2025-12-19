package com.MyMemory.MyMemory.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MyMemory.MyMemory.Enitity.Memory;
import com.MyMemory.MyMemory.Repository.MemoryRepository;

@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    // Create memory
    public Memory createMemory(String title, String description, String category) {
        Memory memory = new Memory();
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setCategory(category);
        memory.setCreatedAt(LocalDateTime.now());
        return memoryRepository.save(memory);
    }

    // Get all memories
    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    // Get memory by ID
    public Memory getMemoryById(Long id) {
        return memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found with id " + id));
    }

    // Update memory
    public Memory updateMemory(Long id, String title, String description, String category) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found with id " + id));
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setCategory(category);
        return memoryRepository.save(memory);
    }

    // Delete memory
    public void deleteMemory(Long id) {
        if (!memoryRepository.existsById(id)) {
            throw new RuntimeException("Memory not found with id " + id);
        }
        memoryRepository.deleteById(id);
    }

    // Fuzzy search by title or category
    public List<Memory> searchMemories(String keyword) {
        return memoryRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }
}
