package com.dsp.restdsl.controller;

import com.dsp.restdsl.model.NumberDto;
import com.dsp.restdsl.service.NumberConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest")
public class NumberConversionController {

    private final NumberConversionService numberConversionService;

    private static final Logger logger = LoggerFactory.getLogger(NumberConversionController.class);

    @Autowired
    public NumberConversionController(NumberConversionService numberConversionService) {
        this.numberConversionService = numberConversionService;
    }

    /**
     * Convierte el número a palabras.
     *
     * @param numberDto El número a convertir en un objeto NumberDto.
     * @return La representación en palabras del número.
     */
    @PostMapping("/convert-number-to-words")
    public ResponseEntity<String> convertNumberToWords(@RequestBody NumberDto numberDto) {
        logger.info("convertNumberToWords endpoint called with number: {}", numberDto.getNumber());

        try {
            if (numberDto.getNumber() == null || numberDto.getNumber().isEmpty()) {
                logger.warn("Number is null or empty.");
                return ResponseEntity.badRequest().body("Number is required.");
            }

            logger.debug("Calling the service to convert number to words: {}", numberDto.getNumber());
            String result = numberConversionService.convertNumberToWords(numberDto.getNumber());
            logger.info("Conversion successful, result: {}", result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error occurred while processing the request: ", e);
            return ResponseEntity.status(500).body("Error processing the request.");
        }
    }

    /**
     * Convierte el número a dólares.
     *
     * @param numberDto El número a convertir en un objeto NumberDto.
     * @return La representación en dólares del número.
     */
    @PostMapping("/convert-number-to-dollars")
    public ResponseEntity<String> convertNumberToDollars(@RequestBody NumberDto numberDto) {
        logger.info("convertNumberToDollars endpoint called with number: {}", numberDto.getNumber());

        try {
            if (numberDto.getNumber() == null || numberDto.getNumber().isEmpty()) {
                logger.warn("Number is null or empty.");
                return ResponseEntity.badRequest().body("Number is required.");
            }

            logger.debug("Calling the service to convert number to dollars: {}", numberDto.getNumber());
            String result = numberConversionService.convertNumberToDollars(numberDto.getNumber());
            logger.info("Conversion successful, result: {}", result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error occurred while processing the request: ", e);
            return ResponseEntity.status(500).body("Error processing the request.");
        }
    }
}
