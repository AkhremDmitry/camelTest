package com.epam.brest.course.dozerDto;

public class CarDozerDto extends AbstractCarDozerDto {

    private String descriptionDto;

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    @Override
    public String toString() {
        return "CarDozerDto{" +
                "carIdDto=" + carIdDto +
                ", registrationPlateDto='" + registrationPlateDto + '\'' +
                ", descriptionDto='" + descriptionDto + '\'' +
                '}';
    }
}
