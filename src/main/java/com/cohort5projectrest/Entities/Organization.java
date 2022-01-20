package com.cohort5projectrest.Entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organizationId;
    private String organizationName;
    private String organizationDescription;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    private List<User> users = new ArrayList<>();
//
//    @OneToMany(mappedBy = "organization")
//    @ToString.Exclude
//    private List<Room> rooms = new ArrayList<>();
//
//    @OneToMany(mappedBy = "organization")
//    @ToString.Exclude
//    private List<Meeting> meetings = new ArrayList<>();

//    public Organization(String organizationName, List<User> users) {
//        this.organizationName = organizationName;
//        this.users = users;
//    }
}
