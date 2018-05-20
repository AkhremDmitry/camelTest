package com.epam.brest.course.dao;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * POJO Call.
 */
@XmlRootElement(name = "call")
public class Call extends AbstractCall{

    /**
     * Property date.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCall;

    /**
     * Constructor Call.
     */
    public Call() {
    }

    /**
     * Constructor Call.
     * @param dateCall Date of call
     * @param description Some description
     * @param address The address from which the call was received
     * @param crewId The crew that was sent to the call.
     */
    public Call(final Date dateCall,
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
                "dateCall=" + dateCall +
                ", callId=" + callId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", crewId=" + crewId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (callId != call.callId) return false;
        if (crewId != call.crewId) return false;
        if (dateCall != null ? !dateCall.equals(call.dateCall) : call.dateCall != null) return false;
        if (description != null ? !description.equals(call.description) : call.description != null) return false;
        return address != null ? address.equals(call.address) : call.address == null;
    }

    @Override
    public int hashCode() {
        int result = callId;
        result = 31 * result + (dateCall != null ? dateCall.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + crewId;
        return result;
    }
}
