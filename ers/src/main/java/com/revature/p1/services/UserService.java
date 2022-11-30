package com.revature.p1.services;

import com.revature.p1.custom_exceptions.InvalidAuthException;
import com.revature.p1.custom_exceptions.InvalidUserException;
import com.revature.p1.daos.UserDAO;
import com.revature.p1.dtos.Principal;
import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.models.User;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void saveUser(NewUserRequest req){

        List<String> usernames=userDAO.findAllUsernames();
        if(!isValidUsername(req.getUsername())) throw new InvalidUserException("Username has to be 8-20 characters long, and the characters _ and . may not appear at the beginning, end, or in pairs.");
        if (usernames.contains(req.getUsername())) throw new InvalidUserException("Username is already taken.");
        if(!isValidPassword(req.getPassword1())) throw new InvalidUserException("Password must contain a of minimum eight characters, one letter and one number:" + req.getPassword1());
        if(!req.getPassword1().equals(req.getPassword2())) throw new InvalidUserException("Passwords do not match.");

        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getEmail(), req.getPassword1(), req.getGiven_name(), req.getSurname(), false, "3");
        userDAO.save(createdUser);
    }

    public Principal login(NewLoginRequest req) {
    User validUser = userDAO.getUserByUserAndPassword(req.getUsername(), req.getPassword());
    if (validUser == null) throw new InvalidAuthException("Invalid username or password");
     return new Principal(validUser.getUser_Id(), validUser.getUsername(), validUser.getRole_id());
}

    private boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isValidPassword(String password) {

      return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}