package com.cohort5projectrest.Controllers;

import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Services.OrganizationService;
import com.cohort5projectrest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private OrganizationService organizationService;

    @Autowired
    public ProjectController(UserService userService, OrganizationService organizationService) {
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user){

        try {

            userService.createUser(user);
            return ResponseEntity.ok("Registered Successfully");

        }catch(IllegalStateException illegalStateException){
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalStateException.getMessage());
        }

    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

//    Users in organization
    @GetMapping("/users/organization/{organizationId}")
    public List<User> getUsersInOrganization(@PathVariable int organizationId){
        return userService.getUsersInOrganization(organizationId);
    }

    //Get organization of user
//    @GetMapping("users/user/{userId}")
//    public ResponseEntity<Organization> getOrganizationOfUser(@PathVariable int userId){
//
//        return ResponseEntity.ok();
//    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId){
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/currentUser/{emailAddress}")
    public ResponseEntity<User> getUserByEmailAddress(@PathVariable String emailAddress){
        User user = userService.getUserByEmail(emailAddress);

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



    /************ ORGANIZATION *****************/
    @PostMapping("/create/organization")
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization){

        organizationService.createOrganization(organization);
        return ResponseEntity.ok(organization);
    }

    @GetMapping("/organizations")
    public List<Organization> getOrganizations(){
        return organizationService.getOrganizations();
    }

}
