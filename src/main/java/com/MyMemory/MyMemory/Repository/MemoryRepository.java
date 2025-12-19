package com.MyMemory.MyMemory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MyMemory.MyMemory.Enitity.Memory;
import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    // Fuzzy search: title or category
    List<Memory> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
}
