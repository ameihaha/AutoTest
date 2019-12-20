package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//测试套件运行之间共有的基础放置
public class SuitConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite  运行啦");

    }
    @AfterSuite
    public  void  afterSuite(){
        System.out.println("afterSuite  运行啦");

    }
}
