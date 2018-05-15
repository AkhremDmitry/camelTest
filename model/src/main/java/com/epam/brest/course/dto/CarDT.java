package com.epam.brest.course.dto;

public class CarDT {

    private int carIdDto;

    private String registrationPlateDto;

    private String descriptionDto;

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

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    @Override
    public String toString() {
        return "CarDT{" +
                "carIdDto=" + carIdDto +
                ", registrationPlateDto='" + registrationPlateDto + '\'' +
                ", descriptionDto='" + descriptionDto + '\'' +
                '}';
    }
}
