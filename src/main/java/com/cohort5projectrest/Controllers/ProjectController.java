package com.cohort5projectrest.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Entities.Room;
import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Services.OrganizationService;
import com.cohort5projectrest.Services.RoomService;
import com.cohort5projectrest.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProjectController {


    private UserService userService;
    private OrganizationService organizationService;
    private RoomService roomService;

    @Autowired
    public ProjectController(UserService userService,
                             OrganizationService organizationService,
                             RoomService roomService) {
        this.userService = userService;
        this.organizationService = organizationService;
        this.roomService = roomService;
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
    @GetMapping("user/{userId}/organization")
    public ResponseEntity<Organization> getOrganizationOfUser(@PathVariable int userId){
        Organization usersOrganization = userService.getUsersOrganization(userId);
        return ResponseEntity.ok(usersOrganization);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int userId){
        Optional<User> user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/currentUser/{emailAddress}")
    public ResponseEntity<User> getUserByEmailAddress(@PathVariable String emailAddress){
        User user = userService.getUserByEmail(emailAddress);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/searchUser")
    public ResponseEntity<List<User>> searchUser(@RequestBody String keyword){
        List<User> searchedUsers = userService.searchUserByName(keyword);
        return ResponseEntity.ok(searchedUsers);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable int userId, @RequestBody User userDetails){
        Optional<User> user = userService.getUserById(userId);

        user.get().setEmployeeFirstName(userDetails.getEmployeeFirstName());
        user.get().setEmployeeEmailAddress(userDetails.getEmployeeEmailAddress());
        user.get().setPassword(userDetails.getPassword());

        User user1 = user.get();
        User updatedUser = userService.createUser(user1);
        return ResponseEntity.ok(Optional.of(updatedUser));
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


    /************ Room *****************/
    @PostMapping("/create_room")
    public Map<String, Room> createRoom(@RequestBody Room room){
        roomService.createRoom(room);
        return Map.of("Room created", room);
    }

}
