package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;
import java.util.Collection;

@Controller
@WebService(endpointInterface = "com.epam.brest.course.soap.CarSoapController",
        serviceName = "CarSoapControllerImpl")
public class CarSoapControllerImpl implements CarSoapController {


    @Autowired
    private CarService carService;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("SOAP: getAllCarsDto()");
        return carService.getAllCarsDto();
    }

    @Override
    public Car getCarById(int carId) {
        LOGGER.debug("SOAP: getCarById({})", carId);
        return carService.getCarById(carId);
    }

    @Override
    public Car addCar(Car car) {
        LOGGER.debug("SOAP: addCar({})", car);
        return carService.addCar(car);
    }

    @Override
    public void updateCar(Car car) {
        LOGGER.debug("SOAP: updateCar({})", car);
        carService.updateCar(car);
    }

    @Override
    public void deleteCarById(int id) {
        LOGGER.debug("SOAP: deleteCarById({})", id);
        carService.deleteCarById(id);
    }

    @Override
    public Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("SOAP: getAllCarsDtoWithCrew()");
        return carService.getAllCarsDtoWithCrew();
    }
}
