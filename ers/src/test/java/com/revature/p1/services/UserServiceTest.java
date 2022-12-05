package com.revature.p1.services;

import com.revature.p1.daos.UserDAO;

import static org.junit.Assert.*;

public class UserServiceTest {
    private final UserService sut = new UserService(new UserDAO());



}