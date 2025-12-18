package com.MyMemory.MyMemory.Services;


import java.util.List;
import org.springframework.stereotype.Service;

import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Repository.UserRepository;


@Service
public class UserService {


private final UserRepository userRepository;


public UserService(UserRepository userRepository) {
this.userRepository = userRepository;
}



public User createUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
        throw new RuntimeException("Email already exists");
    }
    return userRepository.save(user);
}



public List<User> getAllUsers() {
return userRepository.findAll();
}


public User getUserById(Long id) {
return userRepository.findById(id)
.orElseThrow(() -> new RuntimeException("User not found"));
}



public User updateUser(Long id, User user) {
User existingUser = getUserById(id);
existingUser.setName(user.getName());
existingUser.setEmail(user.getEmail());
existingUser.setPassword(user.getPassword());
return userRepository.save(existingUser);
}


public void deleteUser(Long id) {
userRepository.deleteById(id);
}
}