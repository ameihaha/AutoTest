package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXMl {
    @Test
    public void test1() {
        System.out.println("Thread Id1 : " + Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.println("Thread Id2 : " + Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.println("Thread Id3 : " + Thread.currentThread().getId());

    }

}
