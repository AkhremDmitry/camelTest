package com.epam.brest.course.dozerDto;

public abstract class AbstractCrewDozerDto {

    int crewIdDto;

    String crewNameDto;

    public int getCrewIdDto() {
        return crewIdDto;
    }

    public void setCrewIdDto(int crewIdDto) {
        this.crewIdDto = crewIdDto;
    }

    public String getCrewNameDto() {
        return crewNameDto;
    }

    public void setCrewNameDto(String crewNameDto) {
        this.crewNameDto = crewNameDto;
    }
}
