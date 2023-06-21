package com.in28minutes.junit;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    @Test
    public void calculateSum() {

        //실패가 없으면 통과한다.
        int[] numbers = {1,2,3};
        MyMath math = new MyMath();
        int result = math.calculateSum(numbers);
        System.out.println(result);

        int expectedResult = 6; //5로 수정하면 단위 테스트에 실패한다.
//        java.lang.AssertionError:
//        Expected :5
//        Actual   :6

        assertEquals(expectedResult, result);
    }
}