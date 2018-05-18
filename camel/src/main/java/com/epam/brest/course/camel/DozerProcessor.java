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
//import java.util.Map;

public class DozerProcessor implements Processor {

    private Mapper dozMapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();

        String body = message.getBody(String.class);

        if (body == null) return;

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(body);


        if (jsonNode instanceof ArrayNode) {
            if (jsonNode.has("callId")) {


                if (body.contains("callId")) {
                    message.setBody(getMessageForCallDto(body));
                }

                if (body.contains("carId") && body.contains("registrationPlate") && body.contains("description") && !body.contains("numberOfCrew")) {
                    message.setBody(getMessageForCarDto(body));
                }



            }


        } else {
            message.setBody(getBodyForDto(body));
        }


//        mapper.readValue(body, Map.class);



        exchange.setIn(message);
    }


    private String getBodyForDto(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String resBody = body;

        if (body.contains("carId") && body.contains("registrationPlate") && body.contains("description") && !body.contains("numberOfCrew")) {
            Car car = mapper.readValue(body, Car.class);
            CarDT carDt = dozMapper.map(car, CarDT.class);
            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt);
        }
        if (body.contains("callId")){
            Call call = mapper.readValue(body, Call.class);
            CallDto callDto = dozMapper.map(call, CallDto.class);
            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto);
        }


        return resBody;
    }

    private String getMessageForCallDto(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String resBody;


//        Class<Call> c = Call.class;


//        if ('{' == body.charAt(0)) {
//            Call call = mapper.readValue(body, c);
//            CallDto callDto = dozMapper.map(call, CallDto.class);
//            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto);
//        }

//        if ('[' == body.charAt(0)) {
            StringBuilder outJson = new StringBuilder('[');

            Call calls[] = mapper.readValue(body, Call[].class);

            for (int i = 0; i < calls.length; i++) {
                CallDto callDto = dozMapper.map(calls[i], CallDto.class);
                outJson.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(callDto));
                if (i < calls.length - 1) outJson.append(',');
            }

            outJson.append(']');
            resBody = outJson.toString();
//        }
        return resBody;
    }

    private String getMessageForCarDto(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String resBody;

//        if ('{' == body.charAt(0)) {
//            Car car = mapper.readValue(body, Car.class);
//            CarDT carDt = dozMapper.map(car, CarDT.class);
//            resBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt);
//        }

//        if ('[' == body.charAt(0)) {
            StringBuilder outJson = new StringBuilder('[');

            Car cars[] = mapper.readValue(body, Car[].class);

            for (int i = 0; i < cars.length; i++) {
                CarDT carDt = dozMapper.map(cars[i], CarDT.class);
                outJson.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(carDt));
                if (i < cars.length - 1) outJson.append(',');
            }

            outJson.append(']');
            resBody = outJson.toString();
//        }
        return resBody;
    }
}
