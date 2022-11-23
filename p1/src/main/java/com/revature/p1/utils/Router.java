package com.revature.p1.utils;
import com.revature.p1.daos.UserDAO;
import com.revature.p1.handlers.UserHandler;
import com.revature.p1.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void router(Javalin app){
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService);

        app.routes(() ->{
            path("/users", ()  ->  {
                post(userHandler::signup);
                });
            });
        }
    }


