package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Call;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

/**
 * The @WebService annotation on the implementation class lets CXF know which
 * interface to use when creating WSDL. In this case its simply our HelloWorld interface.
 */
@WebService
public interface CallSoapController {

    @WebMethod//annotation optional and is mainly used to provide a name attribute to the public method in wsdl
    Collection<Call> getAllCalls();

}
