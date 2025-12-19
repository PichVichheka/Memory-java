package com.MyMemory.MyMemory.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.MyMemory.MyMemory.Enitity.Memory;
import com.MyMemory.MyMemory.Expception.ResourceNotFoundException;
import com.MyMemory.MyMemory.Repository.MemoryRepository;

import jakarta.transaction.Transactional;

@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    // ------------------ CREATE ------------------
    public Memory createMemory(String title, String description) {
        Memory memory = new Memory();
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setCreatedAt(LocalDateTime.now());
        return memoryRepository.save(memory);
    }

    // ------------------ GET ALL ------------------
    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    // ------------------ GET BY ID ------------------
    public Memory getMemoryById(Long id) {
        return memoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Memory not found with id: " + id));
    }

    // ------------------ UPDATE ------------------
    public Memory updateMemory(Long id, String title, String description) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Memory not found with id: " + id));
        memory.setTitle(title);
        memory.setDescription(description);
        return memoryRepository.save(memory);
    }

    // ------------------ DELETE ------------------
    public void deleteMemory(Long id) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Memory not found with id: " + id));
        memoryRepository.delete(memory);
    }

    // ------------------ SEARCH ------------------
    public List<Memory> searchMemories(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new RuntimeException("Keyword must not be empty");
        }

        List<Memory> results = memoryRepository.searchByTitleOrCategory(keyword);

        if (results.isEmpty()) {
            throw new IllegalArgumentException("No memories found for keyword: " + keyword);
        }

        return results;
    }

    // ------------------ FIX NULL CATEGORIES ------------------
    @Transactional
    public void fixNullCategories() {
        List<Memory> nullCategory = memoryRepository.findAll().stream()
            .filter(m -> m.getCategory() == null)
            .collect(Collectors.toList());

        for (Memory m : nullCategory) {
            m.setCategory("General");
        }

        memoryRepository.saveAll(nullCategory);
    }
}
