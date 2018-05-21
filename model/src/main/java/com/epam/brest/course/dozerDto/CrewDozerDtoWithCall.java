package com.epam.brest.course.dozerDto;

public class CrewDozerDtoWithCall extends AbstractCrewDozerDto {

    private String descriptionDto;

    private int numberOfCallsDto;

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    public int getNumberOfCallsDto() {
        return numberOfCallsDto;
    }

    public void setNumberOfCallsDto(int numberOfCallsDto) {
        this.numberOfCallsDto = numberOfCallsDto;
    }

    @Override
    public String toString() {
        return "CrewDozerDtoWithCall{" +
                "descriptionDto='" + descriptionDto + '\'' +
                ", numberOfCallsDto=" + numberOfCallsDto +
                ", crewIdDto=" + crewIdDto +
                ", crewNameDto='" + crewNameDto + '\'' +
                '}';
    }
}
