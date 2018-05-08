package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Crew;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CrewSoapControllerImplTest {
    private static Server server;
    private final static int PORT = 8070;
    private static WebAppContext context;
    private static CrewSoapController crewSOAP;
    private static String soapServiceUrl = "http://localhost:8070/soap/webCrew";

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
        factoryBean.setServiceClass(CrewSoapController.class);
        factoryBean.setAddress(soapServiceUrl);

        crewSOAP = (CrewSoapController) factoryBean.create();

    }

    @AfterClass
    public static void stop() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void getAllCrewDtoTest(){
        int numOfCrews = crewSOAP.getAllCrewDto().size();

        Assert.assertTrue(numOfCrews>0);
    }

    @Test
    public void getCrewByIdTest(){
        Crew actCrew = crewSOAP.getCrewById(1);

        Assert.assertEquals("Crew1", actCrew.getCrewName());
        Assert.assertEquals("Some Crew1", actCrew.getDescription());
        Assert.assertEquals(1, actCrew.getCarId());
    }

    @Test
    public void addCrewTest(){
        String expName = "CrewAdd";
        String expDescription = "Some CrewAdd";
        int expCarId = 1;

        int expNumOfCrew = crewSOAP.getAllCrewDto().size()+1;
        Crew actCrew = crewSOAP.addCrew(new Crew(expName, expDescription, expCarId));
        int actNumOfCrew = crewSOAP.getAllCrewDto().size();

        Assert.assertEquals(expNumOfCrew, actNumOfCrew);
        Assert.assertEquals(expName, actCrew.getCrewName());
        Assert.assertEquals(expDescription, actCrew.getDescription());
        Assert.assertEquals(expCarId, actCrew.getCarId());
    }

    @Test
    public void updateCrewTest(){
        String expName = "UpdatedCrew";
        String expDescription = "Some UpdatedCrew";
        int expCarId = 2;
        int crewId = 2;
        Crew expCrew = new Crew(expName, expDescription, expCarId);
        expCrew.setCrewId(crewId);

        int expNumOfCrew = crewSOAP.getAllCrewDto().size();
        crewSOAP.updateCrew(expCrew);
        int actNumOfCrew = crewSOAP.getAllCrewDto().size();
        Crew actCrew = crewSOAP.getCrewById(crewId);

        Assert.assertEquals(expNumOfCrew, actNumOfCrew);
        Assert.assertEquals(expCrew, actCrew);
    }

    @Test
    public void deleteCrewByIdTest(){
        int expNumOfCrew = crewSOAP.getAllCrewDto().size()-1;
        crewSOAP.deleteCrewById(3);
        int actNumOfCrew = crewSOAP.getAllCrewDto().size();

        Assert.assertEquals(expNumOfCrew, actNumOfCrew);
    }

    @Test
    public void getAllCrewDtoWithCallTest(){
        int numOfCrews = crewSOAP.getAllCrewDtoWithCall().size();

        Assert.assertTrue(numOfCrews>0);
    }

    @Test
    public void getAllCrewDtoWithCallByDate(){
        int numOfCrews = crewSOAP.getAllCrewDtoWithCallByDate("2018-01-01", "2018-04-01").size();

        Assert.assertTrue(numOfCrews>0);
    }

}
