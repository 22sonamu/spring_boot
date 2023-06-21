package com.in28minutes.junit;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {
    private MyMath math = new MyMath();

    @Test
    public void calculateSum_ThreeMemberArray() {
//        java.lang.AssertionErr
//
//        or:
//        Expected :5
//        Actual   :6

        assertEquals(6, math.calculateSum(new int[] {1,2,3}));
    }

    @Test
    public void calculateSum_ZeroLengthArray() {

        assertEquals(0, math.calculateSum(new int[] {}));
    }

}