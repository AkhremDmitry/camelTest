package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface CrewSoapController {

    @WebMethod
    Collection<CrewDto> getAllCrewDto();

    @WebMethod
    Crew getCrewById(@WebParam(name = "crewId") final int crewId);

    @WebMethod
    Crew addCrew(@WebParam(name = "crew") final Crew crew);

    @WebMethod
    void updateCrew(@WebParam(name = "crew") final Crew crew);

    @WebMethod
    void deleteCrewById(@WebParam(name = "crewId") final int crewId);

    @WebMethod
    Collection<CrewDtoWithCall> getAllCrewDtoWithCall();

    @WebMethod
    Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            @WebParam(name = "startDate") final String startDate,
            @WebParam(name = "endDate") final String endDate);
}
