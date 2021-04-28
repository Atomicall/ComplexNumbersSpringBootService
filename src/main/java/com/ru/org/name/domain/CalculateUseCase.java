package com.ru.org.name.domain;

import com.ru.org.name.domain.interfaces.CalculationService;
import com.ru.org.name.domain.interfaces.Counter;
import com.ru.org.name.domain.interfaces.InternalValidationExceptions;
import com.ru.org.name.domain.interfaces.MapCache;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CalculateUseCase {

    private Logger logger = LoggerFactory.getLogger(CalculateUseCase.class);
    @Autowired
    public MapCache cache;
    @Autowired
    public Counter counter;
    @Autowired
    public CalculationService cs;

    private CalculationResult calculateSingle (double re, double im) throws InternalValidationExceptionsImpl {

        logger.info("_____________________________");
        logger.info("Starting Calculation");
        counter.increaseCounter();
        InputParams inpPar = new InputParams(re, im);

        CalculationResult result = cache.getCalculationResult(inpPar);
        if (result!=null){
            logger.info("Got Info From Cache");
            logger.info("_____________________________");
            return  result;
        }
        else {
            CalculationResult res;

            res = cs.calculate(re, im);

            cache.addCalculationResult(inpPar, res);
            logger.info("Successfully calculated and added to Cache");
            logger.info("_____________________________");
            return res;
        }
    }

    public CalculationResult calculate (double re, double im)
    {
        try {
            return calculateSingle( re, im);
        }
        catch (InternalValidationExceptionsImpl e){
            return null;
        }
    }


}
