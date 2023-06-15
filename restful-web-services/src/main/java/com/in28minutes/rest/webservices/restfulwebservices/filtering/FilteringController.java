package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        //MappingJacksonValue + data class에 @JsonFilter 으로 동적 Filtering을 구현할수있다.
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3"); //field1 과 field3만 허용한다.
        //FilterProvider -> filter를 정의할 수 있게 한다.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list") //
    public MappingJacksonValue filteringList(){
       SomeBean someBean1 = new SomeBean("value1", "value2", "value3");
       SomeBean someBean2 = new SomeBean("value4", "value5", "value6");
       List<SomeBean> someBeanList = Arrays.asList(someBean1, someBean2);
       MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanList);
       SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
       FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
       mappingJacksonValue.setFilters(filterProvider);
       return mappingJacksonValue;
    }


}
