package com.dsp.restdsl.service;

//import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
//import org.apache.camel.component.cxf.jaxws.CxfDataFormat;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

@Component
public class NumberConversionCxfSoapService {

    @Bean
    public CxfEndpoint convertNumber() {
        // Crear el endpoint para CXF
        CxfEndpoint cxfEndpoint = new CxfEndpoint();

        // Establecer la dirección del servicio web
        cxfEndpoint.setAddress("https://www.dataaccess.com/webservicesserver/numberconversion.wso");

        // Establecer la URL WSDL
        cxfEndpoint.setWsdlURL("https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL");

        // Configurar el nombre del puerto (QName) para el servicio web
        cxfEndpoint.setPortNameAsQName(new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversionSoap"));

        // Configuración adicional si es necesario para el formato de datos
        cxfEndpoint.setDataFormat(DataFormat.PAYLOAD);  // Cambié de DataFormat.PAYLOAD a CxfDataFormat.PAYLOAD

        return cxfEndpoint;
    }
}
