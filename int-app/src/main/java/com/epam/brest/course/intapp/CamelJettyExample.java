package com.epam.brest.course.intapp;

import org.apache.camel.main.Main;

public class CamelJettyExample {
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new MyCamelJettyBuilder());
        main.run();


    }
}
