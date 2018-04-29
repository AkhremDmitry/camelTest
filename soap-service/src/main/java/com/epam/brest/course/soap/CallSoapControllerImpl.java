package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;
import java.sql.Date;
import java.util.Collection;

@Controller
@WebService(endpointInterface = "com.epam.brest.course.soap.CallSoapController",
        serviceName = "CallSoapControllerImpl")
public class CallSoapControllerImpl implements CallSoapController {

    @Autowired
    private CallService callService;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public final Collection<Call> getAllCalls() {
        LOGGER.debug("SOAP: getAllCalls()");
        Collection<Call> calls = callService.getAllCall();
        return calls;
    }

    @Override
    public final Collection<Call> getAllCallByDate(final String startDate, final String endDate) {
        LOGGER.debug("SOAP: getAllCallByDate({}, {})", startDate, endDate);
        return callService.getAllCallByDate(Date.valueOf(startDate), Date.valueOf(startDate));
    }

    @Override
    public final Call getCallById(final int id) {
        LOGGER.debug("SOAP: getCallById({})", id);
        return callService.getCallById(id);
    }

    @Override
    public final Call addCall(final Call call) {
        LOGGER.debug("SOAP: addCall({})", call);
        return callService.addCall(call);
    }

    @Override
    public final void updateCall(final Call call) {
        LOGGER.debug("SOAP: updateCall({})", call);
        callService.updateCall(call);
    }

    @Override
    public final void deleteCallById(final int id) {
        LOGGER.debug("SOAP: deleteCallById({})", id);
        callService.deleteCallById(id);
    }

}
