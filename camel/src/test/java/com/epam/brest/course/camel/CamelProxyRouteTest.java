package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dozerDto.CallDozerDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelProxyRouteTest extends CamelSpringTestSupport {

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
    public void routeTest() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        Mapper objectMapper = DozerBeanMapperSingletonWrapper.getInstance();

        Call expCall = new Call();
        expCall.setCallId(8);
        expCall.setAddress("Test address");
        expCall.setDescription("some desc");
        expCall.setCrewId(1);
        CallDozerDto callDozerDto = objectMapper.map(expCall, CallDozerDto.class);
        JsonNode expJson = jsonMapper.valueToTree(expCall);
        JsonNode expJsonDto = jsonMapper.valueToTree(callDozerDto);

        mockDestinationEndpoint.expectedBodiesReceived(expJson.toString());
        mockFileReq.expectedBodiesReceived(expJsonDto.toString());
        mockFileResp.expectedMessageCount(1);

        template.sendBodyAndHeader("direct:saveReqBody",
                expJson.toString(), Exchange.HTTP_URL, "http://localhost:8088/call/1");

        mockDestinationEndpoint.assertIsSatisfied();
        mockFileReq.assertIsSatisfied();
        mockFileResp.assertIsSatisfied();
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-context.xml");
    }

}
