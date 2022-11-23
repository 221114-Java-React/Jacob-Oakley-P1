package com.revature.p1.daos;

import com.revature.p1.models.User;
import io.javalin.http.Context;

import java.util.List;

public class UserDAO implements CrudDAO<User>{
    public void signup(Context c){

    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById() {
        return null;
    }
}
