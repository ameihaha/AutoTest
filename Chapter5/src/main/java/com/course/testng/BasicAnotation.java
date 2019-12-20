package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAnotation {
    //最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void  testCase1(){
        System.out.println("testCase1 这是测试用例1 ");

    }
    public void testCase2(){
        System.out.println("testCase2 这是测试用例2");
    }
    @BeforeMethod
    public  void beforeMethod(){
        System.out.println("BeforeMethod这是在测试方法之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("BeforeMethod在测试方法之后运行的");
    }
}
