package com.ewomail.domain.service;

import com.ewomail.domain.entity.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    
    Users findById(Integer id);
    
    Users findByEmail(String email);
    
    void save(Users user);
    
    void update(Users user);
    
    void deleteById(Integer id);

    void batchInsertUsers(List<Users> userList);
} 