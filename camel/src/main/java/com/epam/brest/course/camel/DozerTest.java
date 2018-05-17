package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CallDto;
import com.epam.brest.course.dto.CarDT;
//import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.sql.Date;

public class DozerTest {

    public static void main(String[] args) {
        Car car = new Car("8888 AA-1", "Car");
        car.setCarId(14);

//        Mapper mapper = new DozerBeanMapper();
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

        CarDT carDT = mapper.map(car, CarDT.class);

        System.out.println("Car --> CarDT: " + carDT);

        Car car2 = mapper.map(carDT, Car.class);

        System.out.println("CarDT --> Car: "+ car2);


        CallDto callDto = new CallDto();
        callDto.setCallIdDto(21);
        callDto.setAddressDto("addr");
        callDto.setDescriptionDto("sdfs");
        callDto.setCrewIdDto(1);
        callDto.setDateCallDto(Date.valueOf("2018-03-21"));

        System.out.println("CallDto = " + callDto);

        Call call = mapper.map(callDto, Call.class);
        System.out.println("CallDto --> Call: " + call);

        CallDto callDto1 = mapper.map(call, CallDto.class);
        System.out.println("Call --> CallDto: " + callDto1);


    }
}
