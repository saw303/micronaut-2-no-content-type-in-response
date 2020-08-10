package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/demo")
public class DemoController {

    @Get
    @Produces("application/json")
    public String read() {
        return "Hello World as JSON";
    }
}
