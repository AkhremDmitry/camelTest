package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;
import java.sql.Date;
import java.util.Collection;

@Controller
@WebService(endpointInterface = "com.epam.brest.course.soap.CrewSoapController",
        serviceName = "CrewSoapControllerImpl")
public class CrewSoapControllerImpl implements CrewSoapController{

    @Autowired
    private CrewService crewService;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("SOAP: getAllCrewDto()");
        return crewService.getAllCrewDto();
    }

    @Override
    public Crew getCrewById(int crewId) {
        LOGGER.debug("SOAP: getCrewById({})", crewId);
        return crewService.getCrewById(crewId);
    }

    @Override
    public Crew addCrew(Crew crew) {
        LOGGER.debug("SOAP: addCrew({})", crew);
        return crewService.addCrew(crew);
    }

    @Override
    public void updateCrew(Crew crew) {
        LOGGER.debug("SOAP: updateCrew({})", crew);
        crewService.updateCrew(crew);
    }

    @Override
    public void deleteCrewById(int crewId) {
        LOGGER.debug("SOAP: deleteCrewById({})", crewId);
        crewService.deleteCrewById(crewId);
    }

    @Override
    public Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("SOAP: getAllCrewDtoWithCall()");
        return crewService.getAllCrewDtoWithCall();
    }

    @Override
    public Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(String startDate, String endDate) {
        LOGGER.debug("SOAP: getAllCrewDtoWithCallByDate({}, {})", startDate, endDate);
        return crewService.getAllCrewDtoWithCallByDate(Date.valueOf(startDate), Date.valueOf(endDate));
    }
}
