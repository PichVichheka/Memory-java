package com.MyMemory.MyMemory.Services;


import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
    	
        return userRepository.save(user);
    }

}
