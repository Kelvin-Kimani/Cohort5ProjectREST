package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmployeeEmailAddress(String emailAddress);

    @Query("SELECT u FROM User u WHERE u.employeeEmailAddress = ?1 AND u.userRole IS NOT NULL")
    User findByEmailAddressAndUserRole(String emailAddress);


    @Query("SELECT u FROM User u WHERE u.organization.organizationId = ?1")
    List<User> findUsersByOrganization(int organizationId);


    @Query("SELECT u FROM User u WHERE u.userRole IS NOT NULL")
    List<User> findAllWithRoles();

    @Query("SELECT u FROM User u WHERE u.userRole IS NULL")
    List<User> findAllWithoutRoles();

    @Modifying
    @Query("UPDATE User u SET u.userRole = :userRole WHERE u.userId = :userId")
    void updateUserRole(@Param(value = "userId") int userId, @Param(value = "userRole") String userRole);

    @Modifying
    @Query("UPDATE User u SET u.employeeFirstName = :firstName, u.employeeLastName = :lastName, u.employeePhoneNumber = :phoneNumber WHERE u.userId = :userId")
    void updateUserDetails(@Param(value = "userId") int userId, @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName, @Param(value = "phoneNumber") String phoneNumber);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.userId = :userId")
    void updateUserPassword(@Param(value = "password") String password, @Param(value = "userId") int userId);

    @Modifying
    @Query("UPDATE User u SET u.userRole = NULL, u.password = NULL WHERE u.userId = :userId")
    void deleteUserRole(@Param(value = "userId") int userId);

    @Query("SELECT COUNT(u.userRole) FROM User u")
    int numberOfUsersWithRoles();

    User findByResetPasswordToken(String token);

    User findBySetPasswordToken(String token);

    @Modifying
    @Query("UPDATE User u SET u.failedAttempts = ?1 WHERE u.employeeEmailAddress = ?2")
    void updateUserFailedAttempts(int failedAttempts, String userEmail);
}
