package com.epam.brest.course.dozerDto;

import java.sql.Date;

public class CallDozerDto {

    private int callIdDto;

    private Date dateCallDto;

    private String descriptionDto;

    private String addressDto;

    private int crewIdDto;


    public int getCallIdDto() {
        return callIdDto;
    }

    public void setCallIdDto(int callIdDto) {
        this.callIdDto = callIdDto;
    }

    public Date getDateCallDto() {
        return dateCallDto;
    }

    public void setDateCallDto(Date dateCallDto) {
        this.dateCallDto = dateCallDto;
    }

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    public String getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(String addressDto) {
        this.addressDto = addressDto;
    }

    public int getCrewIdDto() {
        return crewIdDto;
    }

    public void setCrewIdDto(int crewIdDto) {
        this.crewIdDto = crewIdDto;
    }

    @Override
    public String toString() {
        return "CallDozerDto{" +
                "callIdDto=" + callIdDto +
                ", dateCallDto=" + dateCallDto +
                ", descriptionDto='" + descriptionDto + '\'' +
                ", addressDto='" + addressDto + '\'' +
                ", crewIdDto=" + crewIdDto +
                '}';
    }
}
