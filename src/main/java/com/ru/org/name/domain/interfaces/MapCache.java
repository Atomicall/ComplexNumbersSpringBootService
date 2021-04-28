package com.ru.org.name.domain.interfaces;

import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;


public interface MapCache {
    public CalculationResult getCalculationResult (InputParams pair);
    public void addCalculationResult(InputParams pair, CalculationResult result);
}
