package com.codingdojo.ecommerce.services;

import java.util.Optional;

import com.codingdojo.ecommerce.models.User;
import com.codingdojo.ecommerce.repositories.UserRepo;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UserRepo userRepo;
    
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    

    public User findUser(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    public User updateUser(User b) {
        return userRepo.save(b);
    }

    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Boolean authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user==null) {
            return false; 

        }
        else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
