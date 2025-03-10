package com.ewomail.application.service.impl;

import com.ewomail.application.service.UserApplication;
import com.ewomail.domain.entity.Users;
import com.ewomail.domain.repository.UserRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserApplicationImpl implements UserApplication {

    private final UserRepository userRepository;

    public UserApplicationImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PageInfo<Users> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> users = userRepository.findAll();
        return new PageInfo<>(users);
    }

    @Override
    public Users getById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void create(Users user) {
        user.setCtime(new Date());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(Integer id, Users user) {
        user.setId(id);
        userRepository.update(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void batchCreateUsers(List<Users> userList) {
        userRepository.batchInsertUsers(userList);
    }

    @Override
    public void batchCreateUsers(Integer size, Integer id) {
        Date date = new Date();
        List<Users> userList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Users user = new Users();
            user.setId(i + id);
            user.setDomainId(1);
            user.setPassword("16d7a4fca7442dda3ad93c9a726597e4");
            user.setEmail("test" + (i + id) + "@cnyunlian.com");
            user.setMaildir("/ewomail/mail/vmail/cnyunlian.com/t/e/s/test" + (i + id) + ".20241230");
            user.setUname("test" + (i + id));
            user.setTel("1234567890");
            user.setActive(1);
            user.setLimits(1);
            user.setLimitg(1);
            user.setCtime(date);
            userList.add(user);
            if ((i + 1) % 500 == 0) {
                this.batchCreateUsers(userList);
                userList = new ArrayList<>();
            }
        }
        if (size % 500 != 0) {
            this.batchCreateUsers(userList);
        }
    }
}