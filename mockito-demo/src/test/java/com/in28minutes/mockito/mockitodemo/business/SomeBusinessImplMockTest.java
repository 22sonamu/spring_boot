package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessImplMockTest {

    @Test
    void findTheGreatestFromAllData_basic(){
        DataService dataServiceMock = mock(DataService.class);
        //dataServiceMock.retrieveAllData() => new int[] {25, 15, 5}을 return해야함
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25, 15, 5}); //mock
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
        int result = businessImpl.findTheGreatestFromAllData(); //dataService가 null이기 떄문에 nullPointerException 발생
        assertEquals(25, result);

    }

    @Test
    void findTheGreatestFromAllData_OneValue(){
        DataService dataServiceMock = mock(DataService.class);
        //dataServiceMock.retrieveAllData() => new int[] {25, 15, 5}을 return해야함
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25}); //mock
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
        int result = businessImpl.findTheGreatestFromAllData(); //dataService가 null이기 떄문에 nullPointerException 발생
        assertEquals(25, result);

    }


}
