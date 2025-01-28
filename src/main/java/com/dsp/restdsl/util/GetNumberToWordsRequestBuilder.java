package com.dsp.restdsl.util;

import com.dataaccess.webservicesserver.NumberToDollars;
import com.dataaccess.webservicesserver.NumberToWords;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class GetNumberToWordsRequestBuilder {
    public NumberToWords getNumberToWords(BigInteger number) {
        NumberToWords request = new NumberToWords();
        request.setUbiNum(number);  // Para convertir el número a palabras, sigue usando BigInteger
        return request;
    }

    public NumberToDollars getNumberToDollars(BigDecimal number) {
        NumberToDollars request = new NumberToDollars();

        // Convertir el BigDecimal a String antes de pasarlo al método setDNum

        request.setDNum(number);  // Convertir BigDecimal a String
        return request;
    }
}
