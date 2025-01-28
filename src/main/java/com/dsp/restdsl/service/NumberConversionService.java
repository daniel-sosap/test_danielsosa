package com.dsp.restdsl.service;

import com.dataaccess.webservicesserver.NumberConversion;
import com.dataaccess.webservicesserver.NumberConversionSoapType;
import com.dataaccess.webservicesserver.NumberToDollars;
import com.dataaccess.webservicesserver.NumberToDollarsResponse;
import com.dataaccess.webservicesserver.NumberToWords;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class NumberConversionService {

    private final NumberConversionSoapType numberConversionSoap;

    // Constructor que obtiene la instancia del servicio SOAP
    public NumberConversionService() {
        // Creamos una instancia del cliente SOAP a través de la clase NumberConversion
        NumberConversion numberConversion = new NumberConversion();
        this.numberConversionSoap = numberConversion.getNumberConversionSoap();
    }

    // Método para convertir el número a palabras
    public String convertNumberToWords(String number) {
        try {
            // Convertimos el número recibido a un BigInteger
            BigInteger ubiNum = new BigInteger(number);

            // Creamos la solicitud para convertir el número a palabras
            NumberToWords numberToWords = new NumberToWords();
            numberToWords.setUbiNum(ubiNum); // Asignamos el BigInteger correctamente
            BigInteger big1 = new BigInteger(number);
        // Hacemos la llamada al servicio SOAP y obtenemos el resultado como String
            String result = numberConversionSoap.numberToWords(big1);

            return result; // Retornamos el resultado

        } catch (Exception e) {
            // En caso de error, lanzamos una excepción o retornamos un mensaje de error
            throw new RuntimeException("Error converting number to words", e);
        }
    }

    // Método para convertir el número a dólares
    public String convertNumberToDollars(String number) {
        try {
            // Convertimos el número recibido a un BigDecimal
            BigDecimal dNum = new BigDecimal(number);

            // Creamos una instancia de NumberToDollars
            NumberToDollars numberToDollars = new NumberToDollars();

            // Convertimos BigDecimal a String y lo asignamos a dNum
            String dNumString = dNum.toString(); // Convertir BigDecimal a String
            BigDecimal ubiDec = new BigDecimal(number);

            numberToDollars.setDNum(ubiDec);  // Aquí pasamos el String

            // Hacemos la llamada al servicio SOAP y obtenemos el resultado como String
            String result = numberConversionSoap.numberToDollars(ubiDec);

            return result; // Retornamos el resultado

        } catch (Exception e) {
            // En caso de error, lanzamos una excepción o retornamos un mensaje de error
            throw new RuntimeException("Error converting number to dollars", e);
        }
    }
}
