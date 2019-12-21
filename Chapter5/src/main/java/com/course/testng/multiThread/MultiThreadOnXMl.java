package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXMl {
    @Test
    public void test1() {
        System.out.println("Thread Id : %s%n" + Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.println("Thread Id : %s%n" + Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.println("Thread Id : %s%n" + Thread.currentThread().getId());

    }

}
