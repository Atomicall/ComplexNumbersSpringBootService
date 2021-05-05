package com.ru.org.name.domain.usecases;

import com.ru.org.name.domain.InternalValidationExceptionsImpl;
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

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CalculateUseCase {

    protected Logger logger = LoggerFactory.getLogger(CalculateUseCase.class);
    @Autowired
    public MapCache cache;
    @Autowired
    public Counter counter;
    @Autowired
    public CalculationService cs;

    protected CalculationResult calculateSingle (double re, double im) throws InternalValidationExceptionsImpl {

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

    public MapCache getCache(){
        return cache;
    }

    protected List<CalculationResult> calculateBulk(List<InputParams> inputParamsList) throws InternalValidationExceptions {

        return inputParamsList.stream().
                        map(d -> {
                            try {
                                return calculateSingle(d.getReal(), d.getImagine());
                            } catch (InternalValidationExceptionsImpl internalValidationExceptions) {
                                logger.error("InternalValidationException in"+d.getClass()+"with: Real"+
                                        d.getReal()+" Imagine:"+d.getImagine());
                                return null;
                            }
                        }).
                        collect(Collectors.toList());
        ///// собрать в лист чего? исходных impParams или того, что вернет каждый эл-то после применения map?
    }


    public CalculationResult calculate (double re, double im)
    {
        try {
            return calculateSingle( re, im);
        }
        catch (InternalValidationExceptions e){
            return null;
        }
    }

    public List<CalculationResult> calculate (List<InputParams> inputParamsList)
    {
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info("Starting Bulk Calculation");
        try {
            return calculateBulk(inputParamsList);
        }
        catch (InternalValidationExceptions e){
            logger.error("InternalValidationException");
            return null;
        }
        finally {
            logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
            logger.info("Ending Bulk Calculation");
        }
    }




}
