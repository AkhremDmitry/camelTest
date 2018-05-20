package com.epam.brest.course.dto;


import com.epam.brest.course.dao.AbstractCall;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * POJO Call.
 */
public class CallDtoWithDate extends AbstractCall {

    /**
     * Property date.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCall;

    /**
     * Constructor Call.
     */
    public CallDtoWithDate() {
    }

    /**
     * Constructor Call.
     * @param dateCall Date of call
     * @param description Some description
     * @param address The address from which the call was received
     * @param crewId The crew that was sent to the call.
     */
    public CallDtoWithDate(final Date dateCall,
                           final String description,
                           final String address,
                           final int crewId) {
        this.dateCall = dateCall;
        this.description = description;
        this.address = address;
        this.crewId = crewId;
    }

    /**
     * Get date.
     * @return Date of call.
     */
    public final Date getDateCall() {
        return dateCall;
    }

    /**
     * Set date.
     * @param dateCall Date of call.
     */
    public final void setDateCall(final Date dateCall) {
        this.dateCall = dateCall;
    }

    @Override
    public String toString() {
        return "Call{" +
                "callId=" + callId +
                ", dateCall=" + dateCall +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", crewId=" + crewId +
                '}';
    }

}
