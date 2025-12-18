package com.MyMemory.MyMemory.Controllers;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {


private final UserService userService;


public UserController(UserService userService) {
this.userService = userService;
}


// CREATE
@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
return ResponseEntity.ok(userService.createUser(user));
}


// READ ALL
@GetMapping
public ResponseEntity<List<User>> getAllUsers() {
return ResponseEntity.ok(userService.getAllUsers());
}


// READ BY ID
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
return ResponseEntity.ok(userService.getUserById(id));
}


// UPDATE
@PutMapping("/{id}")
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
return ResponseEntity.ok(userService.updateUser(id, user));
}


// DELETE
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
userService.deleteUser(id);
return ResponseEntity.ok("User deleted successfully");
}
}
