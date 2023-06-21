package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //Mockito 확장 사용
public class SomeBusinessImplMockTest {

    @Mock
    private DataService dataServiceMock;

    @InjectMocks
    private SomeBusinessImpl businessImpl; //의존성 주입 (dataService)


    @Test
    void findTheGreatestFromAllData_basic(){


        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25, 15, 5});
        int result = businessImpl.findTheGreatestFromAllData();
        assertEquals(25, result);

    }

    @Test
    void findTheGreatestFromAllData_OneValue(){
        //DataService dataServiceMock = mock(DataService.class);

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25}); //mock
        //SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
        int result = businessImpl.findTheGreatestFromAllData();
        assertEquals(25, result);

    }


}
