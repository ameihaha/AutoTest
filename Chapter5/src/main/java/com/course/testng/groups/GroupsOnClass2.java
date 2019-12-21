package com.course.testng.groups;


import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu1 (){
        System.out.println("GroupsOnClass222 中的stu1 运行 ");

    }
    public void stu2 (){
        System.out.println("GroupsOnClass222 中的stu2 运行 ");

    }
}
