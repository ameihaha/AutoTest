package com.course.testng.suite;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)//预期执行时间为3000ms
    public void  testSuccess() throws InterruptedException{
        Thread.sleep(2000);

    }
    @Test(timeOut = 2000)
    public void testFailed() throws  InterruptedException{
        Thread.sleep(3000);

    }
}
