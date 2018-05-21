package com.epam.brest.course.dozerDto;

public class CarDozerSimpleDto extends AbstractCarDozerDto{

    @Override
    public String toString() {
        return "CarDozerSimpleDto{" +
                "carIdDto=" + carIdDto +
                ", registrationPlateDto='" + registrationPlateDto + '\'' +
                '}';
    }
}
