package com.epam.brest.course.soap.client;


import com.epam.brest.course.dao.Call;
import com.epam.brest.course.soap.CallSoapController;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Collection;
import java.util.Date;

public class MySOAPClient {

    public static void main(String[] args) {
        testSOAPFromClient();
    }

    /**
     * create client and test soap service
     */
    private static void testSOAPFromClient() {
        String soapServiceUrl = "http://localhost:8080/soap/webCall";

        Date date = new Date(2018-1900,2-1,20);

        Call call = new Call();
        call.setCallId(40);
        call.setAddress("dsadasda");
        call.setCrewId(1);
        call.setDateCall(date);
        call.setDescription("dsfsd");

        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(CallSoapController.class);
        factoryBean.setAddress(soapServiceUrl);

        CallSoapController callSOAP = (CallSoapController) factoryBean.create();

        Collection<Call> result = callSOAP.getAllCalls();
        System.out.println("Result: " + result);
//        System.out.println(callSOAP.getCallById(1));

//        callSOAP.deleteCallById(1);
        System.out.println(callSOAP.addCall(call));
        System.out.println(callSOAP.getAllCalls());
    }
}
