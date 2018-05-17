package com.epam.brest.course.camel;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dto.CallDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

public class DozerProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().getBody(Call.class);
        System.out.println(exchange.getIn());
        System.out.println(exchange.getIn().getBody());

        Message message = exchange.getIn();

        Mapper dozMapper = DozerBeanMapperSingletonWrapper.getInstance();
        ObjectMapper mapper = new ObjectMapper();

        Call call = mapper.readValue(exchange.getIn().getBody().toString(), Call.class);
        System.out.println(call);
        CallDto callDto = dozMapper.map(call, CallDto.class);
        System.out.println(callDto);
        message.setBody("{\"callId\":1111111,\"dateCall\":1519333200000,\"description\":\"Some description3\",\"address\":\"Some address3\",\"crewId\":1}");
        exchange.setIn(message);


    }
}
