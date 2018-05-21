package com.epam.brest.course.dozerDto;

public class CarDozerDtoWithCrew extends AbstractCarDozerDto{

    private String descriptionDto;

    private int numberOfCrewDto;

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    public int getNumberOfCrewDto() {
        return numberOfCrewDto;
    }

    public void setNumberOfCrewDto(int numberOfCrewDto) {
        this.numberOfCrewDto = numberOfCrewDto;
    }

    @Override
    public String toString() {
        return "CarDozerDtoWithCrew{" +
                "descriptionDto='" + descriptionDto + '\'' +
                ", numberOfCrewDto=" + numberOfCrewDto +
                ", carIdDto=" + carIdDto +
                ", registrationPlateDto='" + registrationPlateDto + '\'' +
                '}';
    }
}
