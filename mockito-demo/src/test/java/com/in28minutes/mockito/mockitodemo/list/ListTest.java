package com.in28minutes.mockito.mockitodemo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void simpleTest(){
        List listMock = mock(List.class);

        //listMock.size() => 3을 return하게 하고싶음
        when(listMock.size()).thenReturn(3);
        assertEquals(3, listMock.size());
    }

    @Test
    public void multipleReturns(){
        List listMock = mock(List.class);

        //listMock.size() => 3을 return하게 하고싶음
        when(listMock.size()).thenReturn(1).thenReturn(2);
        assertEquals(1, listMock.size()); //처음 반환값은 1
        assertEquals(2, listMock.size()); //두번째 반환값은 2 //이후 기본값은 2
    }

    @Test
    public void parameters(){ //파라미터가 있는경우
        List listMock = mock(List.class);


        when(listMock.get(0)).thenReturn("SomeStringBack");
        assertEquals("SomeStringBack", listMock.get(0));
        assertEquals(null, listMock.get(1)); //정의되지 않은 반환값은 null로 반환한다.

    }

    @Test
    public void genericParameter(){ //무슨 파라미터던 같은 값은 return하길 원한다면
        List listMock = mock(List.class);

        when(listMock.get(Mockito.anyInt())).thenReturn("SomeStringBack"); //무슨 파라미터던 같은 값은 return하길 원한다면
        assertEquals("SomeStringBack", listMock.get(100));


    }
}
