package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Repositories.OrganizationRepository;
import com.cohort5projectrest.Repositories.UserRepository;
import com.cohort5projectrest.Validators.EmailValidator;
import com.cohort5projectrest.Validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private UserRepository userRepository;
    private OrganizationRepository organizationRepository;
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    public UserService(UserRepository userRepository, OrganizationRepository organizationRepository){
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    CREATE
    Implementing HTTP POST
    */
    public User createUser(User user){
        Optional<User> userByEmail = Optional.ofNullable(userRepository.findByEmployeeEmailAddress(user.getEmployeeEmailAddress()));
        if (userByEmail.isPresent()){
            throw new IllegalStateException("Account with the same email exists!");
        }

        //check if the email and password valid
        String email = user.getEmployeeEmailAddress();
        String password = user.getPassword();

        if (emailValidator.validate(email)){

            if (passwordValidator.validate(password)){
                //let's encode the password first
                String encodedPassword = PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .encode(password);

                //set password as encoded
                user.setPassword(encodedPassword);

                userRepository.save(user);
                return user;

            } else throw new IllegalStateException("Password does not meet the required criteria");

        } else throw new IllegalStateException("Email Address is not valid");
        
    }

    /*READ*/
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersInOrganization(int organizationId){
        //check if organization exists
        boolean exists = organizationRepository.existsById(organizationId);
        if (exists){
            return userRepository.findUsersByOrganization(organizationId);
        } else {
            throw new IllegalStateException("Organization with id " + organizationId + " doesn't exist");
        }
    }

    public Organization getUsersOrganization(int userId){
        //get user first
        Optional<User> userById = getUserById(userId);

        return userById.get().getOrganization();
    }

    public Optional<User> getUserById(int id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()){
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }

        return userById;

    }

    public User getUserByEmail(String email) {
        Optional<User> userByEmail = Optional.ofNullable(userRepository.findByEmployeeEmailAddress(email));

        if (userByEmail.isEmpty()){
            throw new IllegalStateException("User with the email " + email + " doesn't exist");
        }

        User user = userByEmail.get();
        return user;
    }

    public List<User> searchUserByName(String keyword){
        List<User> searchedUsers = userRepository.searchUser(keyword);
        if (searchedUsers.isEmpty()){
            throw new IllegalStateException("Users with keyword " + keyword + " not found");
        }

        return searchedUsers;
    }
    
    /*Check Email*/
    public boolean validEmail(String email){
        boolean valid = emailValidator.validate(email);

        if (valid){
            return true;
        } else return false;
    }


    /*Check Password*/
    public boolean validPassword(String password){
        boolean valid = passwordValidator.validate(password);

        if (valid){
            return true;
        } else return false;
    }

    /*DELETE*/
    public void deleteById(int id) {
        boolean userExists = userRepository.existsById(id);
        if (!userExists){
            throw new IllegalStateException("User by id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

}
