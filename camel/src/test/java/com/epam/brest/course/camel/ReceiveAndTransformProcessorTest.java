package com.epam.brest.course.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;

public class ReceiveAndTransformProcessorTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:destinationEndpoint")
    private MockEndpoint mockDestinationEndpoint;

    @EndpointInject(uri = "mock:receiveAndTransformProcessor")
    private MockEndpoint mockReceiveAndTransformProcessor;

    @BeforeMethod
    public void setUpContext() throws Exception {
        context.getRouteDefinition("saveBody").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("destinationEndpoint").replace()
                        .to("mock:destinationEndpoint");
                weaveById("receiveAndTransformProcessor").replace()
                        .to("mock:receiveAndTransformProcessor");
            }
        });
    }

    @Test
    public void camelRouteTest(){

    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {


        return null;
    }

    @Override
    public boolean isCreateCamelContextPerClass() {
        return true;
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }
}
