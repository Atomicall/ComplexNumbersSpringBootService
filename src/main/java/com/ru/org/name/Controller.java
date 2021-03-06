package com.ru.org.name;

import com.ru.org.name.data.database.AnswerEntity;
import com.ru.org.name.domain.interfaces.AnswerRepository;
import com.ru.org.name.domain.interfaces.MapCache;
import com.ru.org.name.domain.usecases.CalculateUseCase;
import com.ru.org.name.domain.InternalValidationExceptionsImpl;
import com.ru.org.name.domain.interfaces.Counter;
import com.ru.org.name.domain.usecases.CalculateWithStatisticUsecase;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.CalculationResultsListWithStatistic;
import com.ru.org.name.models.InputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;


@Validated // проверять ограничения на параметрах метода.
@RestController
public class Controller {

    private Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private CalculateUseCase calculateUseCase;
    @Autowired
    private CalculateWithStatisticUsecase calculateWithStatisticUsecase;
    @Autowired
    Counter counter;

    @Autowired
    AnswerRepository repository;

    @RequestMapping("/calculate")
    public CalculationResult calculate(@RequestParam(value = "RealPart", required = true) @Min(-1000)  double real, // Min для галочки и отработки исключений
                                       @RequestParam(value = "ImgPart", required = true) @Min(-1000) double imagine)
           throws InternalValidationExceptionsImpl
    {
        return (calculateUseCase.calculate(real, imagine));
    }

    @RequestMapping("/getCount")
    public Integer GetCount()
    {
        return counter.getCounter();
    }


    @PostMapping("/bulkCalculate")
    public List<CalculationResult> bCalculate(@RequestBody List<InputParams> inputParamsList){
            return calculateUseCase.calculate(inputParamsList);
    }

    @PostMapping("/bulkCalculateWithStat")
    public CalculationResultsListWithStatistic bCalculateWithStatistic(@RequestBody List<InputParams> inputParamsList){
        return calculateWithStatisticUsecase.calculateWithStatistic(inputParamsList);
    }



    @GetMapping("/getFromDB")
    public List<AnswerEntity> getAnswers(){
        return repository.findAll();
    }
    @PostMapping("/writeDB")
    public void writeAnswers() {
       MapCache cache =  calculateUseCase.getCache();
       int id = 0;
       for (InputParams kpg: cache.getQuerries()) {
           AnswerEntity answerEntity = new AnswerEntity();
           answerEntity.setAnswerId(id++);
           answerEntity.setModule_value(cache.getCalculationResult(kpg).getModule());
           answerEntity.setPhase_value(cache.getCalculationResult(kpg).getPhase());
           answerEntity.setImagine(kpg.getImagine());
           answerEntity.setReal(kpg.getReal());
           repository.save(answerEntity);
       }
       cache.clear();
    }





    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

        logger.warn(e.getMessage());
        logger.info ("Validation Error in Controller");
        return new ResponseEntity<>("Not validated due to InputParams Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException e) {
        logger.info ("Missing Param in Controller");
        return new ResponseEntity<>("MissingParam: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalValidationExceptionsImpl.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalValidationException(MissingServletRequestParameterException e) {
        logger.info ("InternalValidation Error");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    }

