package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Repositories.OrganizationRepository;
import com.cohort5projectrest.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    //mock the repository
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrganizationRepository organizationRepository;
    private UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, organizationRepository);
    }

    @Test
    void createUser() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );
        //when
        given(userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress()))
                .willReturn(null);

        userService.createUser(user);


        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        //verify the method saved is called
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();
        //then

        assertThat(capturedUser).isNotNull();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void createUserWillThrowException() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );
        //when
        given(userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress()))
                .willReturn(user);

        //then
        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Account with the same email exists!");

        //verify the save method is never called
        verify(userRepository, never()).save(any());

    }



    @Test
    void getUsers() {
        //given
        userService.getUsers();


        //then
        //verify the method was called
        verify(userRepository).findAll();
    }

    @Test
    void getUsersInOrganization() {

        //given
        Organization organization = new Organization(
                "Tracom Limited Services",
                "Payment Partner"
        );

        //when
        given(organizationRepository.existsById(organization.getOrganizationId()))
                .willReturn(true);

        userService.getUsersInOrganization(organization.getOrganizationId());

        //then
        //verify this was called
        verify(userRepository).findUsersByOrganization(organization.getOrganizationId());

    }

    @Test
    void getUsersInOrganizationWillThrowException() {

        //given
        Organization organization = new Organization(
                "Tracom Limited Services",
                "Payment Partner"
        );

        //when
        given(organizationRepository.existsById(organization.getOrganizationId()))
                .willReturn(false);

        //then
        assertThatThrownBy(() -> userService.getUsersInOrganization(organization.getOrganizationId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Organization with id " + organization.getOrganizationId() + " doesn't exist");

    }


    @Test
    void getUsersOrganization() {

    }

    @Test
    void getUserById() {

        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );

        given(userRepository.findById(user.getUserId()))
                .willReturn(Optional.of(user));

        //when
        Optional<User> userById = userService.getUserById(user.getUserId());

        //then
        assertThat(Optional.of(user)).isEqualTo(userById);

    }


    @Test
    void getUserByIdWillThrowAnException() {

        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );

        given(userRepository.findById(user.getUserId()))
                .willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> userService.getUserById(user.getUserId()))
                .isInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("User with id " + user.getUserId() + " does not exist.");

    }

    @Test
    void getUserByEmail() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );


        given(userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress()))
                .willReturn(user);

        //when
        User userByEmail = userService.getUserByEmail(user.getEmployeeEmailAddress());

        //then
        assertThat(userByEmail).isEqualTo(user);
    }


    @Test
    void getUserByEmailWillThrowAnException() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );


        given(userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress()))
                .willReturn(null);


        //then

        assertThatThrownBy(() -> userService.getUserByEmail(user.getEmployeeEmailAddress()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User with the email " + user.getEmployeeEmailAddress() + " doesn't exist");
    }

    @Test
    void deleteById() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );

        //verify this was called

        given(userRepository.existsById(user.getUserId()))
                .willReturn(true);

        //call method
        userService.deleteById(user.getUserId());

        //then
        //verify this method was called
        verify(userRepository).deleteById(user.getUserId());

    }

    @Test
    void deleteByIdWillThrowException() {
        //given
        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4"
        );

        //verify this was called

        given(userRepository.existsById(user.getUserId()))
                .willReturn(false);

        //call method

        //then

        assertThatThrownBy(() ->  userService.deleteById(user.getUserId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User by id " + user.getUserId() + " does not exist.");

        //verify this method was never called
        verify(userRepository, never()).deleteById(user.getUserId());

    }



}