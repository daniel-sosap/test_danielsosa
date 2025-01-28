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

    public NumberConversionService() {
        NumberConversion numberConversion = new NumberConversion();
        this.numberConversionSoap = numberConversion.getNumberConversionSoap();
    }

    public String convertNumberToWords(String number) {
        try {
            BigInteger ubiNum = new BigInteger(number);
            NumberToWords numberToWords = new NumberToWords();
            numberToWords.setUbiNum(ubiNum);
            BigInteger big1 = new BigInteger(number);
            String result = numberConversionSoap.numberToWords(big1);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Error converting number to words", e);
        }
    }

    public String convertNumberToDollars(String number) {
        try {

            BigDecimal dNum = new BigDecimal(number);
            NumberToDollars numberToDollars = new NumberToDollars();
            String dNumString = dNum.toString();
            BigDecimal ubiDec = new BigDecimal(number);
            numberToDollars.setDNum(ubiDec);
            String result = numberConversionSoap.numberToDollars(ubiDec);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Error converting number to dollars", e);
        }
    }
}
