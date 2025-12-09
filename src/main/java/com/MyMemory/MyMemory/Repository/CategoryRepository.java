package com.MyMemory.MyMemory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyMemory.MyMemory.Enitity.category;

public interface CategoryRepository extends JpaRepository<category, Long> {

}
