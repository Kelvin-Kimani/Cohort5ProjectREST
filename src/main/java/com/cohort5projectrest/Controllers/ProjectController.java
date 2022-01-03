package com.cohort5projectrest.Controllers;

import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProjectController {


    private UserService userService;

    @Autowired
    public ProjectController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId){
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails){
        User user = userService.getUserById(userId);

        user.setEmployeeFirstName(userDetails.getEmployeeFirstName());
        user.setEmployeeEmailAddress(userDetails.getEmployeeEmailAddress());
        user.setPassword(userDetails.getPassword());

        User updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int userId){

        userService.deleteById(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
