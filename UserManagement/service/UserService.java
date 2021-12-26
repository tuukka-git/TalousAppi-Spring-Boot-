package com.tuukkal.talousappi.UserManagement.service;

import com.tuukkal.talousappi.UserManagement.domain.Role;
import com.tuukkal.talousappi.UserManagement.domain.User;

import java.util.List;


public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
