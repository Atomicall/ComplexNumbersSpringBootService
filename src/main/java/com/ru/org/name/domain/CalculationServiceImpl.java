package com.ru.org.name.domain;

import com.ru.org.name.data.Counter;
import com.ru.org.name.interfaces.CalculationService;
import com.ru.org.name.interfaces.MapCache;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component// Для создания как cache, так и CSImpl как бинов с пом. Applcontext
public class CalculationServiceImpl implements CalculationService
{




    @Autowired// инжекнуть значение
    private MapCache cache;
    @Autowired
    private Counter counter;

    private Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);


    private double getQuarter(double re, double im) {
        double quater = 0;

        if (re<0)
        {
            if (im>0)
            {
                quater = Math.PI;
            }
            else
            {
                quater = -Math.PI;
            }
        }
        return quater;
    }

    private double calculateMod(double re, double im) {
        return Math.sqrt(re*re + im*im);
    } //

    private double calculatePhase(double re, double im) {
        return Math.atan(im/re)+ getQuarter(re, im);
    }

    @Override
    public CalculationResult calculate(double re, double im) throws InternalValidationExceptionsImpl
    {
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
            result = new CalculationResult (calculatePhase(re, im), calculateMod(re, im));
            cache.addCalculationResult(inpPar, result);
            logger.info("Successfully calculated and added to Cache");
            logger.info("_____________________________");
            return result;
        }
    }

}
