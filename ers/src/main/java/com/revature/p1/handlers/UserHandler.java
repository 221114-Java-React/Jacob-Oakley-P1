package com.revature.p1.handlers;


import com.fasterxml.jackson.databind.*;
import com.revature.p1.custom_exceptions.InvalidUserException;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.services.UserService;
import io.javalin.http.Context;

import java.io.IOException;

public class UserHandler {
    private final UserService userService;
    private final ObjectMapper mapper;

    public UserHandler(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    public void signup(Context c) throws IOException {

        NewUserRequest req = mapper.readValue(c.req.getInputStream(), NewUserRequest.class);
        try {
            userService.saveUser(req);
            c.status(201);
        } catch (InvalidUserException e) {
            c.status(403);
            c.json(e);
        }


    }
}
