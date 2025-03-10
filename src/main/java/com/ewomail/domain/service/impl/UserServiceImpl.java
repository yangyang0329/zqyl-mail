package com.ewomail.domain.service.impl;

import com.ewomail.domain.entity.Users;
import com.ewomail.domain.repository.UserRepository;
import com.ewomail.domain.service.UserService;
import com.ewomail.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return null;
    }

    @Override
    public void save(Users user) {

    }

    @Override
    public void update(Users user) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void batchInsertUsers(List<Users> userList) {
        userRepository.batchInsertUsers(userList);
    }
}