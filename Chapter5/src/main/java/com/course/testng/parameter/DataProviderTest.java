package com.course.testng.parameter;

import org.testng.SuiteRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


/**
 * dataProvider参数化，通过dataProvidor对象进行参数传递
 */
public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age) {
        System.out.println("name = " + name + ";age = " + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        Object[][] o = new Object[][]{
                {"zhangsan", 10},
                {"lisi", 20},
                {"wangwu", 30},
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1方法 name = " + name + "; age =  " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2方法 name = " + name + "; age = " + age);
    }

    //根据不同的method，传递不同的参数
    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"zhangsan", 20},
                    {"lisi", 25},
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"wangwu", 50},
                    {"zhaoliu", 60},
            };

        }
        return result;
    }
}
