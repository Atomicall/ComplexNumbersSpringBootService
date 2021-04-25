package com.ru.org.name.domain;

import com.ru.org.name.cache.Counter;
import com.ru.org.name.cache.MapCacheImpl;
import com.ru.org.name.interfaces.CalculationService;
import com.ru.org.name.interfaces.MapCache;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculationServiceImpl implements CalculationService
{

    private Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);

    @Autowired// не надо думать, как передать сюда экземпляр другого класса
    MapCacheImpl Cache;

    private double getQuater(double re, double im) {
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
        return Math.atan(im/re)+ getQuater(re, im);
    }

    @Override
    public CalculationResult calculate(double re, double im) throws InternalValidationExceptionsImpl
    {
        logger.info("Starting Calculation");
        Counter.increaseCounter();

        InputParams inpPar = new InputParams(re, im); // Храним вход парамы



        logger.info("Successfully calculated");;
        return new CalculationResult(calculatePhase(re, im), calculateMod(re, im));
    }

}


/*
package com.ru.org.name;
public class Calculate {

    private final long id;
    private final String content;

    public Calculate(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}*/
