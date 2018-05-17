package com.epam.brest.course.camel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("camel-context.xml");
        ctx.getBean("dozerProcessor");
    }
}
