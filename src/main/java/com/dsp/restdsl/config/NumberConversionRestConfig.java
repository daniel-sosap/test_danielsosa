package com.dsp.restdsl.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.camel.Exchange;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteConfigurationBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NumberConversionRestConfig extends RouteConfigurationBuilder {

    @Override
    public void configuration() throws Exception {

        routeConfiguration("number-conversion-rest-config")
                .onException(Exception.class)
                .handled(true)
                .process(this::numberConversionExceptionHandler)
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpResponseCode", constant(400))
                .end();
    }

    public void numberConversionExceptionHandler(Exchange exchange) {
        // Usar Map.of para inicializar Map de manera más limpia (si no se necesita modificar el mapa)
        Map<String, Object> errorResponse = new HashMap<>();
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        errorResponse.put("message", "Validation error");

        if (exception instanceof ValidationException validationException) {  // Cambio de BeanValidationException a ValidationException
            // Usando la nueva sintaxis de Java 17 para declarar la variable directamente
            String errors = validationException.getMessage();
            errorResponse.put("errors", errors);
        } else if (exception instanceof UnrecognizedPropertyException unrecognizedException) {
            errorResponse.put("errors", "The '" + unrecognizedException.getPropertyName() + "' property is not recognized.");
        } else if (exception instanceof JsonParseException) {
            errorResponse.put("errors", "Invalid JSON format.");
        } else {
            errorResponse.put("errors", "An unexpected error occurred. Please try again later.");
        }

        // Actualizamos el cuerpo del mensaje con la respuesta de error
        exchange.getIn().setBody(errorResponse);
    }
}
