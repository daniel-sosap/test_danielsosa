package com.dsp.restdsl.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamelRouteController {

    @Autowired
    private CamelContext camelContext;

    @GetMapping("/routes")
    public String getRoutes() throws Exception {
        return camelContext.getRoutes().toString();
    }
}
