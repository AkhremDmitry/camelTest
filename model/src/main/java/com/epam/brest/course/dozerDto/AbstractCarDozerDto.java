package com.epam.brest.course.dozerDto;

public abstract class AbstractCarDozerDto {

    int carIdDto;

    String registrationPlateDto;

    public int getCarIdDto() {
        return carIdDto;
    }

    public void setCarIdDto(int carIdDto) {
        this.carIdDto = carIdDto;
    }

    public String getRegistrationPlateDto() {
        return registrationPlateDto;
    }

    public void setRegistrationPlateDto(String registrationPlateDto) {
        this.registrationPlateDto = registrationPlateDto;
    }
}
