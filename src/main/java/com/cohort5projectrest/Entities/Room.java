package com.cohort5projectrest.Entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
@JsonIncludeProperties({"roomId", "roomName", "roomCapacity", "whiteboard", "displayScreen", "conferencePhone"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomName;
    private int roomCapacity;
    private String whiteboard;
    private String displayScreen;
    private String conferencePhone;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;

//    @OneToMany(mappedBy = "room")
//    @ToString.Exclude
//    private List<Meeting> meeting;

}
