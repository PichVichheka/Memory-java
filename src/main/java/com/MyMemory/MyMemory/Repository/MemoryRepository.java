package com.MyMemory.MyMemory.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.MyMemory.MyMemory.Enitity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    @Query("SELECT m FROM Memory m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Memory> searchByTitleOrCategory(@Param("keyword") String keyword);
}
