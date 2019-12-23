package com.course.testng;

import org.testng.annotations.*;

public class BasicAnotation {
    //最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void  testCase1(){
        System.out.printf("Thread Id1 : %s%n" , Thread.currentThread().getId());
        System.out.println("testCase1 这是测试用例1 ");

    }
    @Test
    public void testCase2(){
        System.out.println("testCase2 这是测试用例2");
    }
    @BeforeMethod
    public  void beforeMethod(){
        System.out.println("BeforeMethod 这是在测试方法之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod 这是在测试方法之后运行的");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass 这是在类运行之前运行的");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass 这是在类运行之后运行的");
    }
    @BeforeSuite
    public void beforeSuit(){
        System.out.println("BeforeSuite 这是测试套件");
    }
    @AfterSuite
    public void  afterSuit(){
        System.out.println("AfterSuite 这是测试套件");
    }

}
