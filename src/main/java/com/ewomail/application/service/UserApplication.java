package com.ewomail.application.service;

import com.ewomail.domain.entity.Users;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface UserApplication {
    PageInfo<Users> list(int pageNum, int pageSize);
    
    Users getById(Integer id);
    
    void create(Users user);
    
    void update(Integer id, Users user);
    
    void delete(Integer id);
    
    void batchCreateUsers(List<Users> userList);
    void batchCreateUsers(Integer size, Integer id);
}
