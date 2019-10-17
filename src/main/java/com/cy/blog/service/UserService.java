package com.cy.blog.service;

import com.cy.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
