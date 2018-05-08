package com.epam.brest.course.soap;

import com.epam.brest.course.dao.Car;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarSoapControllerImplTest {

    private static Server server;
    private final static int PORT = 8070;
    private static WebAppContext context;
    private static CarSoapController carSOAP;
    private static String soapServiceUrl = "http://localhost:8070/soap/webCar";

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
        factoryBean.setServiceClass(CarSoapController.class);
        factoryBean.setAddress(soapServiceUrl);

        carSOAP = (CarSoapController) factoryBean.create();

    }

    @AfterClass
    public static void stop() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void getAllCarsDtoTest(){
        int numberOfCars = carSOAP.getAllCarsDto().size();

        Assert.assertTrue(numberOfCars>0);
    }


    @Test
    public void getCarByIdTest(){
        Car expCar = new Car("8888 II-1", "testGetCar");
        expCar = carSOAP.addCar(expCar);

        Car actCar = carSOAP.getCarById(expCar.getCarId());

        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void addCarTest(){
        Car expCar = new Car("7777 II-1", "testAddCar");
        int expNumOfCar = carSOAP.getAllCarsDto().size()+1;
        expCar = carSOAP.addCar(expCar);
        int actNumOfCar = carSOAP.getAllCarsDto().size();

        Car actCar = carSOAP.getCarById(expCar.getCarId());

        Assert.assertEquals(expNumOfCar, actNumOfCar);
        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void updateCarTest(){
        Car expCar = new Car("1111 II-1", "testUpdateCar");
        int carId = carSOAP.addCar(new Car("6666 II-1", "testAddCar")).getCarId();
        expCar.setCarId(carId);
        int expNumOfCar = carSOAP.getAllCarsDto().size();

        carSOAP.updateCar(expCar);

        int actNumOfCar = carSOAP.getAllCarsDto().size();

        Car actCar = carSOAP.getCarById(carId);

        Assert.assertEquals(expNumOfCar, actNumOfCar);
        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void deleteCarById(){
        int carId = carSOAP.addCar(new Car("5555 II-1", "testDelCar")).getCarId();
        int expNumOfCar = carSOAP.getAllCarsDto().size()-1;

        carSOAP.deleteCarById(carId);

        int actNumOfCar = carSOAP.getAllCarsDto().size();

        Assert.assertEquals(expNumOfCar, actNumOfCar);
    }

    @Test
    public void getAllCarsDtoWithCrew(){
        int numberOfCarDtos = carSOAP.getAllCarsDto().size();

        Assert.assertTrue(numberOfCarDtos>0);
    }


}
