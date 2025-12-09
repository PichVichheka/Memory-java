package com.MyMemory.MyMemory.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MyMemory.MyMemory.Enitity.Memory;
import com.MyMemory.MyMemory.Repository.MemoryRepository;

@Service   // ✅ THIS LINE FIXES EVERYTHING
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public Memory createMemory(String title, String description) {
        Memory memory = new Memory();
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setCreatedAt(LocalDateTime.now());
        return memoryRepository.save(memory);
    }

    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    public Optional<Memory> getMemoryById(Long id) {
        return memoryRepository.findById(id);
    }

    public Memory updateMemory(Long id, String title, String description) {
        Optional<Memory> optionalMemory = memoryRepository.findById(id);
        if (optionalMemory.isPresent()) {
            Memory memory = optionalMemory.get();
            memory.setTitle(title);
            memory.setDescription(description);
            return memoryRepository.save(memory);
        }
        return null;
    }

    public void deleteMemory(Long id) {
        memoryRepository.deleteById(id);
    }
}
