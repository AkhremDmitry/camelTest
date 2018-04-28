package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Call;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collection;

@WebService(endpointInterface = "com.epam.brest.course.soap.CallSoapController",
        serviceName = "CallSoapControllerImpl")
public class CallSoapControllerImpl implements CallSoapController {

    @Override
    public Collection<Call> getAllCalls() {

        return Arrays.asList(new Call(), new Call());
    }
}
