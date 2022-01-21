package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Entities.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

//Test on in-memory db
@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    void findByEmployeeEmailAddress() {

        //given
        User user = new User();
        user.setEmployeeEmailAddress("kelvinkim996@gmail.com");
        user.setEmployeeFirstName("Kelvin");
        user.setEmployeeLastName("Kimani");
        user.setUserRole("Admin");
        user.setPassword("JayJay");

        userRepository.save(user);

        //when
        User retrievedUser = userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress());

        //then
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser).isEqualTo(user);
    }

    @Test
    void findUsersByOrganization(){
        //given
        Organization organization = new Organization(
                "Tracom Limited Services",
                "Payment Partner"
        );

        User user = new User(
                "Admin",
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com",
                "+254790838747",
                "14141H17c4",
                organization
        );

        User user1 = new User(
                "Admin",
                "Jeff",
                "Bezos",
                "bezos@gmail.com",
                "+254790838747",
                "14141H17c4",
                organization
        );


        //when

        //save both users and organization
        userRepository.saveAll(List.of(user, user1));
        organizationRepository.save(organization);

        List<User> usersByOrganization = userRepository.findUsersByOrganization(organization.getOrganizationId());

        //then
        assertThat(usersByOrganization).isNotEmpty();
        assertThat(usersByOrganization).containsAll(List.of(user, user1));
        assertThat(usersByOrganization).hasSize(2);
    }
}