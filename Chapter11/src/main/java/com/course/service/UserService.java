package com.course.service;


import com.course.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper mapper;
    public int   getUserCount(){
        return mapper.getUserCount();
    }
}
