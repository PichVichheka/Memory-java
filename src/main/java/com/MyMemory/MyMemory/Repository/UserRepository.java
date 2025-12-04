package com.MyMemory.MyMemory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyMemory.MyMemory.Enitity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
