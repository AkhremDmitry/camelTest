package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dozerDto.*;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ReceiveAndTransformProcessor implements Processor {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private Mapper objectMapper;

    @Autowired
    private ObjectMapper jsonMapper;


    @Override
    public void process(Exchange exchange) throws Exception {

        Message message = exchange.getIn();
        LOGGER.debug("Received message: {}", message);
        String sendBody = message.getBody(String.class);

        if (sendBody == null || "".equals(sendBody)) {
            return;
        }
        JsonNode sendJson = jsonMapper.readTree(sendBody);


        if (sendJson instanceof ArrayNode) {
            ArrayNode arrayNode = jsonMapper.createArrayNode();
            for (JsonNode curNode : sendJson) {
                arrayNode.add(getResultJson(curNode));
            }
            sendBody = arrayNode.toString();
        } else {
            sendJson = getResultJson(sendJson);
            sendBody = sendJson.toString();
        }

        message.setBody(sendBody);
        exchange.setIn(message);
        LOGGER.debug("Sent message: {}", message);
    }

    private JsonNode getResultJson(JsonNode sendJson) throws IOException {
        final Class fromClazz;
        final Class toClazz;
        if (sendJson.has("callId")) {
            fromClazz = Call.class;
            toClazz = CallDozerDto.class;
        } else if (sendJson.has("carId") && sendJson.has("registrationPlate")
                && sendJson.has("description") && !sendJson.has("numberOfCrew")) {
            fromClazz = Car.class;
            toClazz = CarDozerDto.class;
        } else if (sendJson.has("carId") && sendJson.has("registrationPlate")
                && sendJson.has("description") && sendJson.has("numberOfCrew")) {
            fromClazz = CarDtoWithCrew.class;
            toClazz = CarDozerDtoWithCrew.class;
        } else if (sendJson.has("carId") && sendJson.has("registrationPlate")
                && !sendJson.has("description") && !sendJson.has("numberOfCrew")) {
            fromClazz = CarDto.class;
            toClazz = CarDozerSimpleDto.class;
        } else if (sendJson.has("crewId") && sendJson.has("crewName") && sendJson.has("carId")) {
            fromClazz = Crew.class;
            toClazz = CrewDozerDto.class;
        } else if (sendJson.has("crewId") && sendJson.has("crewName") && !sendJson.has("description")) {
            fromClazz = CrewDto.class;
            toClazz = CrewDozerSimpleDto.class;
        } else if (sendJson.has("crewId") && sendJson.has("crewName") && sendJson.has("numberOfCalls")) {
            fromClazz = CrewDtoWithCall.class;
            toClazz = CrewDozerDtoWithCall.class;
        } else {
            throw new RuntimeException("Unrecognized model for " + sendJson);
        }
        Object parsedModel = jsonMapper.treeToValue(sendJson, fromClazz);
        Object resultObjectDto = objectMapper.map(parsedModel, toClazz);
        return jsonMapper.valueToTree(resultObjectDto);
    }

}
