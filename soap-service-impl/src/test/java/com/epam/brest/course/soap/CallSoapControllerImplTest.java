package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Call;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.Date;

public class CallSoapControllerImplTest {

    private static Server server;
    private final static int PORT = 8070;
    private static WebAppContext context;
    private static CallSoapController callSOAP;
    private static String soapServiceUrl = "http://localhost:8070/soap/webCall";

    @BeforeClass
    public static void init() throws Exception {
        server = new Server(PORT);
        context = new WebAppContext();
        context.setDescriptor("src/test/resources/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();

        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(CallSoapController.class);
        factoryBean.setAddress(soapServiceUrl);

        callSOAP = (CallSoapController) factoryBean.create();

    }

    @AfterClass
    public static void stop() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void getAllCallsTest(){
        int numberOfCalls = callSOAP.getAllCalls().size();

        Assert.assertTrue(numberOfCalls>0);
    }

    @Test
    public void getAllCallByDateTest(){
        int numberOfCalls = callSOAP.getAllCallByDate("2018-01-01", "2018-04-01").size();

        Assert.assertTrue(numberOfCalls>0);
    }

    @Test
    public void getCallByIdTest(){
        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2018-3-14"));
        expCall.setDescription("Some description");
        expCall.setAddress("Address");
        expCall.setCrewId(1);
        int callId = callSOAP.addCall(expCall).getCallId();

        Call actCall = callSOAP.getCallById(callId);

        Assert.assertEquals(expCall.getAddress(), actCall.getAddress());
        Assert.assertEquals(expCall.getDescription(), actCall.getDescription());
        Assert.assertEquals(expCall.getCrewId(), actCall.getCrewId());
        Assert.assertEquals(expCall.getDateCall(), actCall.getDateCall());
    }

    @Test
    public void addCallTest(){
        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2017-2-10"));
        expCall.setDescription("Some for addTest");
        expCall.setAddress("Address addTest");
        expCall.setCrewId(1);
        int expNumOfCalls = callSOAP.getAllCalls().size() + 1;
        Call actCall = callSOAP.addCall(expCall);
        int actNumOfcalls = callSOAP.getAllCalls().size();
        Assert.assertEquals(expNumOfCalls, actNumOfcalls);

        Assert.assertEquals(expCall.getAddress(), actCall.getAddress());
        Assert.assertEquals(expCall.getDescription(), actCall.getDescription());
        Assert.assertEquals(expCall.getCrewId(), actCall.getCrewId());
        Assert.assertEquals(expCall.getDateCall(), actCall.getDateCall());
    }

    @Test
    public void updateCall(){
        Call call = new Call();
        call.setDateCall(Date.valueOf("2016-07-22"));
        call.setDescription("Prepare for update test");
        call.setAddress("Address");
        call.setCrewId(1);

        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2018-05-04"));
        expCall.setDescription("update test");
        expCall.setAddress("Address for update");
        expCall.setCrewId(1);
        int updatedCallId = callSOAP.addCall(call).getCallId();
        expCall.setCallId(updatedCallId);

        int expNumOfCalls = callSOAP.getAllCalls().size();

        callSOAP.updateCall(expCall);

        Call actCall = callSOAP.getCallById(updatedCallId);
        int actNumOfCalls = callSOAP.getAllCalls().size();

        Assert.assertEquals(expNumOfCalls, actNumOfCalls);

        Assert.assertEquals(expCall.getAddress(), actCall.getAddress());
        Assert.assertEquals(expCall.getDescription(), actCall.getDescription());
        Assert.assertEquals(expCall.getCrewId(), actCall.getCrewId());
        Assert.assertEquals(expCall.getDateCall(), actCall.getDateCall());
    }

    @Test
    public void deleteCallById(){
        Call call = new Call(Date.valueOf("2018-05-04"), "delete", "Address for delete", 1);
        call = callSOAP.addCall(call);
        int expNumOfCalls = callSOAP.getAllCalls().size()-1;

        callSOAP.deleteCallById(call.getCallId());
        int actNumOfCalls = callSOAP.getAllCalls().size();

        Assert.assertEquals(expNumOfCalls, actNumOfCalls);
    }



}
