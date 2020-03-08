package com.course.model;

import lombok.Data;

@Data  //为字段提供set和get属性
public class User {


    private  int id;
    private  String userName;
    private  String password;
    private  String age;
    private  String sex;
    private  String permission;
    private  String isDelete;



    //重写toString方法，自定义返回json格式的方法
    @Override
    public String  toString(){
        return (
                "id:" + id + "," +
                        "userName:" + userName + "," +
                        "password:" + password + "," +
                        "age:" + age + "," +
                        "sex:" + sex + "," +
                        "permission:" + permission + "," +
                        "isDelete:" + isDelete + ","

                );
    }
}
