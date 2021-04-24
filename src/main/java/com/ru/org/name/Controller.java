package com.ru.org.name;


import com.ru.org.name.domain.CalculationServiceImpl;
import com.ru.org.name.domain.InternalValidationExceptionsImpl;
import com.ru.org.name.models.CalculationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated // проверять ограничения на параметрах метода.
@RestController
public class Controller {

    private Logger logger = LoggerFactory.getLogger(Controller.class);


    @RequestMapping("/calculate")
    public CalculationResult calculate(@RequestParam(value = "RealPart", required = true) @Min(-1000)  double real, // Min для галочки и отработки исключений
                                       @RequestParam(value = "ImgPart", required = true) @Min(-1000) double imagine)
           throws InternalValidationExceptionsImpl
    {
        return (new CalculationServiceImpl()).calculate(real, imagine);
    }

    /*@RequestMapping("/e")
    public ResponseEntity<String> ERR()
    {
        return new ResponseEntity<String> ("DD", HttpStatus.BAD_REQUEST);
    }*/
    @ExceptionHandler(ConstraintViolationException.class) // 400 Bag_Request
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Т.к спринг по-умолчанию при несоотв параметров переданных как выше кидает ответ 500 и искл
    // ConstraintViolationException. Автоматически они не обрабатываются
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

        logger.warn(e.getMessage());
        logger.info ("Validatio Error in Controller");
        return new ResponseEntity<>("Not validated due to InputParams Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException e) {
        logger.info ("Missing Param in Controller");
        return new ResponseEntity<>("MissingParam: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    }

