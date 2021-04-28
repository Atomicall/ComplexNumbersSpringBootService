package com.ru.org.name.domain;

import com.ru.org.name.data.CounterImpl;
import com.ru.org.name.domain.interfaces.CalculationService;
import com.ru.org.name.domain.interfaces.MapCache;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculationServiceImpl implements CalculationService
{
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
        CalculationResult result = new CalculationResult (calculatePhase(re, im), calculateMod(re, im));
        return result;
    }

}
