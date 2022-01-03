package com.cohort5projectrest.Entities;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userRole;

    //User defined
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDepartment;
    private String employeeEmailAddress;
    private String employeePhoneNumber;
    private String password;

    //Generated
    private String resetPasswordToken;
    private String setPasswordToken;

    //Limit Login Attempts
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean accountNonLocked = true;

    private int failedAttempts;
    private Date lockTime;

//    @ManyToOne
//    @JoinColumn(name = "organization_id")
//    @ToString.Exclude
//    private Organization organization;
//
//    @ManyToMany(mappedBy = "users")
//    @ToString.Exclude
//    private List<Meeting> meetings;
}
