package com.ewomail.interfaces.controller;

import com.ewomail.application.service.UserApplication;
import com.ewomail.domain.entity.Users;
import com.ewomail.interfaces.dto.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @GetMapping("/list")
    public Result<PageInfo<Users>> list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(userApplication.list(pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public Result<Users> getById(@PathVariable Integer id) {
        return Result.success(userApplication.getById(id));
    }


    @PostMapping
    public Result<Void> create(@RequestBody Users user) {
        userApplication.create(user);
        return Result.success(null);
    }

    @PostMapping("/batchCreateUsers")
    public Result<Void> batchCreateUsers(@RequestParam Integer size, @RequestParam Integer id) {
        log.info("param is : size is :{},id is :{}", size, id);
        userApplication.batchCreateUsers(size, id);
        return Result.success(null);
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Integer id, @RequestBody Users user) {
        userApplication.update(id, user);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        userApplication.delete(id);
        return Result.success(null);
    }
} 