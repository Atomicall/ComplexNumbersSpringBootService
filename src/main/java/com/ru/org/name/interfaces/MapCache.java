package com.ru.org.name.interfaces;

import com.ru.org.name.Controller;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


public interface MapCache {
    public CalculationResult getCalculationResult (InputParams pair);
    public void addCalculationResult(InputParams pair, CalculationResult result);
}
