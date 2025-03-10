package com.ewomail.infrastructure.repository;

import com.ewomail.domain.entity.Users;
import com.ewomail.domain.repository.UserRepository;
import com.ewomail.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<Users> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public Users findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public void save(Users user) {
        userMapper.insert(user);
    }

    @Override
    public void update(Users user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void batchInsertUsers(List<Users> userList) {
        userMapper.batchInsertUsers(userList);
    }
} 