package com.epam.brest.course.intapp;

import org.apache.camel.builder.RouteBuilder;

public class MyCamelJettyBuilder extends RouteBuilder {
    public void configure() {
        from("jetty:http://0.0.0.0:8080?matchOnUriPrefix=true")
                .to("http://localhost:8090?bridgeEndpoint=true");
    }
}