package com.revature.p1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.UserDAO;
import com.revature.p1.handlers.AuthHandler;
import com.revature.p1.handlers.UserHandler;
import com.revature.p1.services.TokenService;
import com.revature.p1.services.UserService;
import io.javalin.Javalin;

import javax.naming.Context;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void router(Javalin app){
        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService, mapper);
        AuthHandler authHandler = new AuthHandler(userService, tokenService, mapper);

        app.routes(() ->{
            path("/users", ()  ->  {
                post(context -> userHandler.signup(context));

                });


            path("/auth", () -> {
                post(authHandler::authenticateUser);


            });
        });
        }
    }


