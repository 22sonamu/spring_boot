package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeBusinessImplTest {

    @Test
    void findTheGreatestFromAllData_basic(){
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
        int result = businessImpl.findTheGreatestFromAllData(); //dataService가 null이기 떄문에 nullPointerException 발생
        assertEquals(25, result);

    }

    @Test
    void findTheGreatestFromAllData_withOneValue(){
        DataServiceStub2 dataServiceStub = new DataServiceStub2();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
        int result = businessImpl.findTheGreatestFromAllData(); //dataService가 null이기 떄문에 nullPointerException 발생
        assertEquals(35, result);

    }
}
class DataServiceStub implements DataService{

    @Override
    public int[] retrieveAllData() {
        return new int[]{25, 15, 5};
    }

    //만약 DataService에 새 메서드가 추가되었다면 이 테스트 유닛을 사용하지 않더라도 여기에 새 메서드를 구현해야한다. (Stub 의 문제점) -> 대안 : Mock
}

class DataServiceStub2 implements DataService{

    @Override
    public int[] retrieveAllData() {
        return new int[]{35};
    }


}