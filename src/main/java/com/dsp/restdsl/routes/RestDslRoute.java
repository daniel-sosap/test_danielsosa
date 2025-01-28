package com.dsp.restdsl.routes;

import com.dataaccess.webservicesserver.NumberToDollarsResponse;
import com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.dsp.restdsl.model.NumberDto;
import com.dsp.restdsl.util.GetNumberToWordsRequestBuilder;
import com.dsp.restdsl.util.NumberConversionHeaderUtil;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class RestDslRoute extends RouteBuilder {

    private static final String NUMBER_TO_WORDS_ROUTE = "direct:number-to-words";
    private static final String NUMBER_TO_WORDS_DOLLARS = "direct:number-to-dollars";
    private static final String NUMBER_SERVICE_URI = "cxf:bean:convertNumber";

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .host("localhost")
                .port(8080)
                .component("servlet")
                .inlineRoutes(true)
                .bindingMode(RestBindingMode.auto)
                .dataFormatProperty("prettyPrint", "true")
                .apiContextPath("/api-docs")
                .apiProperty("api.title", "Number Conversion - Camel REST DSL and CXF SOAP")
                .apiProperty("api.version", "1.0")
                .apiContextRouteId("api-doc-context")
                .apiVendorExtension(true);

        rest("/api")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .description("Number Conversion REST service")


                .post("/convert-number-to-words")
                .description("Converts a number to words")
                .type(NumberDto.class)
                .to(NUMBER_TO_WORDS_ROUTE)


                .post("/convert-number-to-dollars")
                .description("Converts a number to dollars")
                .type(NumberDto.class)
                .to(NUMBER_TO_WORDS_DOLLARS);

        from(NUMBER_TO_WORDS_ROUTE)
                .routeConfigurationId("number-conversion-rest-config")
                .to("bean-validator://validateNumberDto")
                .process(exchange -> {
                    NumberDto numberDto = exchange.getIn().getBody(NumberDto.class);
                    BigInteger number = new BigInteger(numberDto.getNumber());
                    exchange.getIn().setBody(number);
                })
                .bean(GetNumberToWordsRequestBuilder.class, "getNumberToWords")
                .marshal().jaxb()
                .process(NumberConversionHeaderUtil::setNumberToWordsHeader)
                .to(NUMBER_SERVICE_URI)
                .process(exchange -> {
                    NumberToWordsResponse response = exchange.getIn().getBody(NumberToWordsResponse.class);
                })
                .unmarshal().jaxb("com.dataaccess.webservicesserver")
                .marshal().json()
                .end();

        from(NUMBER_TO_WORDS_DOLLARS)
                .routeConfigurationId("number-conversion-rest-config")
                .to("bean-validator://validateNumberDto")
                .process(exchange -> {
                    NumberDto numberDto = exchange.getIn().getBody(NumberDto.class);
                    BigDecimal number = new BigDecimal(numberDto.getNumber());
                    exchange.getIn().setBody(number);
                })
                .bean(GetNumberToWordsRequestBuilder.class, "getNumberToDollars")
                .marshal().jaxb()
                .process(NumberConversionHeaderUtil::setNumberToDollarsHeader)
                .to(NUMBER_SERVICE_URI)
                .process(exchange -> {
                    NumberToDollarsResponse response = exchange.getIn().getBody(NumberToDollarsResponse.class);
                })
                .unmarshal().jaxb("com.dataaccess.webservicesserver")
                .marshal().json()
                .end();
    }
}
