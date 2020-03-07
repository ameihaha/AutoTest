package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",description = "第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    //获取执行SQL语句对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public  int getUserCount(){
        System.out.println("啊啊啊啊啊啊啊啊啊");
       int  result = template.selectOne("getUserCount");
        System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊+   "+ result);
        return  result; //这里匹配resources/mapper/mysql.xml里的select标签的id

    }
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
//        int result  = template.insert("addUser",user);
//        return  result;
        return  template.insert("addUser",user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);

    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public int delUser(@RequestParam int id ){
        return  template.delete("deleteUser");

    }


}
