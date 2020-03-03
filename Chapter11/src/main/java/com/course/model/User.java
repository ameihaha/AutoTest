package com.course.model;

import lombok.Data;

@Data//lombok插件提供的，需要给每个字段提供set和get属性。
public class User {
    private  int  id;
    private String name;
    private String sex;
    private int  age;


}
