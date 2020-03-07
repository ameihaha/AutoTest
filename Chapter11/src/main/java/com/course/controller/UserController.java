package com.course.controller;

import com.course.model.User;
import com.course.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Log4j
@RestController
@Api(value = "v1",description = "第一个版本的demo")
@RequestMapping("v1")
public class UserController {
    
    @Resource
    private UserService userservice;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public  int getUserCount(){
        System.out.println("啊啊啊啊啊啊啊啊啊");
       int  result = userservice.getUserCount();
        System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊+   "+ result);
        return  result; //这里匹配resources/mapper/mysql.xml里的select标签的id

    }

}
