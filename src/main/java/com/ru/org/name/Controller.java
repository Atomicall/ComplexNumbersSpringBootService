package com.ru.org.name;


import com.ru.org.name.domain.CalculationServiceImpl;
import com.ru.org.name.models.CalculationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;


@Validated
@RestController
public class Controller {

    private Logger logger = LoggerFactory.getLogger(Controller.class);


    @RequestMapping("/calculate")
    public CalculationResult calculate(@RequestParam(value = "RealPart", required = false) @Min(0)  double real,
                                       @RequestParam(value = "ImgPart", required = false) @Min(0) double imagine) {
        return (new CalculationServiceImpl()).calculate(real, imagine);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

        logger.warn(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
