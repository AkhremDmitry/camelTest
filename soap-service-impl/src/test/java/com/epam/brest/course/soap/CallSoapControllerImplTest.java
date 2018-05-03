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
    private final static int PORT = 8080;
    private static WebAppContext context;
    private static CallSoapController callSOAP;
    private static String soapServiceUrl = "http://localhost:8080/soap/webCall";

    @BeforeClass
    public static void init() throws Exception {
        server = new Server(PORT);
        context = new WebAppContext();
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
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



}
