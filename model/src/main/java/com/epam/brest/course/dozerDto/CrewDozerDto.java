package com.epam.brest.course.dozerDto;

public class CrewDozerDto extends AbstractCrewDozerDto{

    /**
     * Property description.
     */
    private String descriptionDto;

    /**
     * Property carId.
     */
    private int carIdDto;

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    public int getCarIdDto() {
        return carIdDto;
    }

    public void setCarIdDto(int carIdDto) {
        this.carIdDto = carIdDto;
    }

    @Override
    public String toString() {
        return "CrewDozerDto{" +
                "crewIdDto=" + crewIdDto +
                ", crewNameDto='" + crewNameDto + '\'' +
                ", descriptionDto='" + descriptionDto + '\'' +
                ", carIdDto=" + carIdDto +
                '}';
    }
}
