package com.in28minutes.junit;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyAssetTest {

    List<String> todos = Arrays.asList("AWS", "Azure", "Devops");

    @Test
    public void test(){
        boolean test = todos.contains("AWS");
        assertTrue(test); //test가 true 이면 통과
        assertEquals("Error Message", true, test);
    }
}
