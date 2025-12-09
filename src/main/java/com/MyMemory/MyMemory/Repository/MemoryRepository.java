package com.MyMemory.MyMemory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyMemory.MyMemory.Enitity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long>{

}
