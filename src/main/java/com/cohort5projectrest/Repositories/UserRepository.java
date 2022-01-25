package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmployeeEmailAddress(String emailAddress);

    @Query("SELECT u FROM User u WHERE u.organization.organizationId = ?1")
    List<User> findUsersByOrganization(int organizationId);

    @Query("SELECT u FROM User u WHERE u.employeeFirstName LIKE %?1%" +
            "OR u.employeeLastName LIKE %?1%" +
            "OR u.employeeEmailAddress LIKE %?1%")
    List<User> searchUser(String keyword);
}
