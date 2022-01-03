package com.cohort5projectrest.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class Room{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomName;
    private int roomCapacity;
    private String whiteboard;
    private String displayScreen;
    private String conferencePhone;

//    @ManyToOne
//    @JoinColumn(name = "organization_id")
//    @ToString.Exclude
//    private Organization organization;
//
//    @OneToMany(mappedBy = "room")
//    @ToString.Exclude
//    private List<Meeting> meeting;

}
