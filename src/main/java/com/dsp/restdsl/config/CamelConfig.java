
package com.dsp.restdsl.config;

//import com.dsp.restdsl.routes.RestDslRoute;
import com.dsp.restdsl.routes.RestDslRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.dsp.restdsl.routes")
public class CamelConfig {

    @Bean
    public CamelContext camelContext() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.addRoutes(new RestDslRoute());
        return context;
    }
}

