package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    @Query("SELECT m FROM Meeting m WHERE m.meetingId =?1")
    Meeting findMeetingByMeetingId(int meetingId);

    void deleteById(Integer meetingId);
}
