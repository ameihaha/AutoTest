package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXMl {
    @Test
    public void test1() {
        System.out.printf("Thread Id1 : %s%n" , Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.printf("Thread Id2 : %s%n " , Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.printf("Thread Id3 : %s%n " , Thread.currentThread().getId());

    }

}
