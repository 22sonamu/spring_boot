package com.in28minutes.junit;

import org.junit.*;

public class MyBeforeAfterTest {

    @BeforeClass //junit 4 -> BeforeClass , junit 5 -> BeforeAll
     public static void beforeAll(){
        System.out.println("BeforeAll");
    }

    @Before //테스트를 수행하기 전에 항상 출력된다.  //junit 4 -> Before , junit 5 -> BeforeEach
    public void beforeEach(){
        System.out.println("BeforeEach");
    }
    //순서를 보장하지는 않는다.
    @Test
    public void test1(){
        System.out.println("test1");
    }
    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
    @After//테스트를 수행한 후에 항상 출력된다. //junit 4 -> After , junit 5 -> AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }

    @AfterClass //junit 4 -> AfterClass , junit 5 -> AfterAll
    public static void afterAll(){
        System.out.println("afterAll");
    }



}
