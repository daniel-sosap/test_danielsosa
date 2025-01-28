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
        CxfEndpoint cxfEndpoint = new CxfEndpoint();

        cxfEndpoint.setAddress("https://www.dataaccess.com/webservicesserver/numberconversion.wso");
        cxfEndpoint.setWsdlURL("https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL");
        cxfEndpoint.setPortNameAsQName(new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversionSoap"));
        cxfEndpoint.setDataFormat(DataFormat.PAYLOAD);

        return cxfEndpoint;
    }
}
