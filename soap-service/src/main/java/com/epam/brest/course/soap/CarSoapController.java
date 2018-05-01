package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface CarSoapController {

    @WebMethod
    Collection<CarDto> getAllCarsDto();

    @WebMethod
    Car getCarById(@WebParam(name = "carId") final int carId);

    @WebMethod
    Car addCar(@WebParam(name = "car") final Car car);

    @WebMethod
    void updateCar(@WebParam(name = "car") final Car car);

    @WebMethod
    void deleteCarById(@WebParam(name = "id") final int id);

    @WebMethod
    Collection<CarDtoWithCrew> getAllCarsDtoWithCrew();

}
