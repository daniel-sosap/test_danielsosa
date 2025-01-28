package com.dsp.restdsl.model;

//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.dsp.restdsl.validation.IsNumeric;

//import javax.validation.constraints.NotNull;

@Data
public class NumberDto {

    @NotNull(message = "Number is required")
    @IsNumeric(message = "Number must be numeric", fieldName = "number")
    private String number;
}
