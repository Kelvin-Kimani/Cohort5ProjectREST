package com.cohort5projectrest.Entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonIncludeProperties({"userId", "userRole", "employeeFirstName", "employeeLastName", "employeeEmailAddress", "employeePhoneNumber", "password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userRole;

    //User defined
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeEmailAddress;
    private String employeePhoneNumber;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
//    @JsonManagedReference

    private Organization organization;
//
//    @ManyToMany(mappedBy = "users")
//    @ToString.Exclude
//    private List<Meeting> meetings;


    public User(String userRole, String employeeFirstName, String employeeLastName, String employeeEmailAddress, String employeePhoneNumber, String password, Organization organization) {
        this.userRole = userRole;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmailAddress = employeeEmailAddress;
        this.employeePhoneNumber = employeePhoneNumber;
        this.password = password;
        this.organization = organization;
    }

    public User(String userRole, String employeeFirstName, String employeeLastName, String employeeEmailAddress, String employeePhoneNumber, String password) {
        this.userRole = userRole;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmailAddress = employeeEmailAddress;
        this.employeePhoneNumber = employeePhoneNumber;
        this.password = password;
    }
}
