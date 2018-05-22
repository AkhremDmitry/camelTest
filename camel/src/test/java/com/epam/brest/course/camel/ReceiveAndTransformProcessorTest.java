package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiveAndTransformProcessorTest extends CamelSpringTestSupport {

    @EndpointInject(uri = "mock:destinationEndpoint")
    private MockEndpoint mockDestinationEndpoint;

    @EndpointInject(uri = "mock:fileReq")
    private MockEndpoint mockFileReq;

    @EndpointInject(uri = "mock:fileResp")
    private MockEndpoint mockFileResp;

    @Before
    public void setUpContext() throws Exception {
        context.getRouteDefinitions().get(1).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("destinationEndpoint").replace()
                        .to("mock:destinationEndpoint");
                weaveById("fileReq").replace()
                        .to("mock:fileReq");
                weaveById("fileResp").replace()
                        .to("mock:fileResp");
            }
        });

        context.start();
    }

    @Test
    public void testMoveFile() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        Call expCall = new Call();
        expCall.setCallId(8);
        expCall.setAddress("sdfsa");
        expCall.setDescription("some desc");
        expCall.setCrewId(1);
        JsonNode expJson = jsonMapper.valueToTree(expCall);

        mockDestinationEndpoint.expectedMessageCount(0);
        mockDestinationEndpoint.expectedBodiesReceived(expJson.toString());

        template.sendBodyAndHeader("direct:saveReqBody",
                expJson.toString(), Exchange.HTTP_URL, "http://localhost:8088/call/1");

        mockDestinationEndpoint.assertIsSatisfied();

    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-context.xml");
    }

}
