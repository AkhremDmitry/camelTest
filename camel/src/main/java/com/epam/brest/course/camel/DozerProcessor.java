package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CallDto;
import com.epam.brest.course.dto.CarDT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.io.IOException;

public class DozerProcessor implements Processor {

    private Mapper dozMapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public void process(Exchange exchange) throws Exception {

        Message message = exchange.getIn();
        String body = message.getBody(String.class);
        ObjectMapper mapper = new ObjectMapper();

        if (body == null) return;
        JsonNode jsonNode = mapper.readTree(body);

        if (jsonNode instanceof ArrayNode) {
            ArrayNode arrayNode = mapper.createArrayNode();
            for (JsonNode curNode : jsonNode) {
                arrayNode.add(get(curNode));
            }
            body = arrayNode.toString();
        } else {
            jsonNode = get(jsonNode);
            body = jsonNode.toString();
        }
        message.setBody(body);
        exchange.setIn(message);
    }

    private JsonNode get(JsonNode body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode resultJsonNode = body;
        if (body.has("callId")) {
            Call call = mapper.treeToValue(body, Call.class);
            CallDto callDto = dozMapper.map(call, CallDto.class);
            resultJsonNode = mapper.valueToTree(callDto);
        }
        if (body.has("carId") && body.has("registrationPlate")
                && body.has("description") && !body.has("numberOfCrew")) {
            Car car = mapper.treeToValue(body, Car.class);
            CarDT carDt = dozMapper.map(car, CarDT.class);
            resultJsonNode = mapper.valueToTree((carDt));
        }
        return resultJsonNode;
    }

//    private String getBodyForDto(String body) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String resBody = body;
//
//        if (body.contains("carId") && body.contains("registrationPlate") && body.contains("description") && !body.contains("numberOfCrew")) {
//            Car car = mapper.readValue(body, Car.class);
//            CarDT carDt = dozMapper.map(car, CarDT.class);
//            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt);
//        }
//        if (body.contains("callId")) {
//            Call call = mapper.readValue(body, Call.class);
//            CallDto callDto = dozMapper.map(call, CallDto.class);
//            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto);
//        }
//
//
//        return resBody;
//    }

//    private String getMessageForCallDto(String body) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        String resBody;
//
//
////        Class<Call> c = Call.class;
//
//
////        if ('{' == body.charAt(0)) {
////            Call call = mapper.readValue(body, c);
////            CallDto callDto = dozMapper.map(call, CallDto.class);
////            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto);
////        }
//
////        if ('[' == body.charAt(0)) {
//        StringBuilder outJson = new StringBuilder('[');
//
//        Call calls[] = mapper.readValue(body, Call[].class);
//
//        for (int i = 0; i < calls.length; i++) {
//            CallDto callDto = dozMapper.map(calls[i], CallDto.class);
//            outJson.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto));
//            if (i < calls.length - 1) outJson.append(',');
//        }
//
//        outJson.append(']');
//        resBody = outJson.toString();
////        }
//        return resBody;
//    }

//    private String getMessageForCarDto(String body) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        String resBody;
//
////        if ('{' == body.charAt(0)) {
////            Car car = mapper.readValue(body, Car.class);
////            CarDT carDt = dozMapper.map(car, CarDT.class);
////            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt);
////        }
//
////        if ('[' == body.charAt(0)) {
//        StringBuilder outJson = new StringBuilder('[');
//
//        Car cars[] = mapper.readValue(body, Car[].class);
//
//        for (int i = 0; i < cars.length; i++) {
//            CarDT carDt = dozMapper.map(cars[i], CarDT.class);
//            outJson.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt));
//            if (i < cars.length - 1) outJson.append(',');
//        }
//
//        outJson.append(']');
//        resBody = outJson.toString();
////        }
//        return resBody;
//    }
}
