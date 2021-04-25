package com.ru.org.name.cache;

import com.ru.org.name.Controller;
import com.ru.org.name.interfaces.MapCache;
import com.ru.org.name.models.CalculationResult;
import com.ru.org.name.models.InputParams;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.logging.Logger;


@Component
public class MapCacheImpl implements MapCache {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Controller.class);
    private HashMap<InputParams, CalculationResult> theMap = new HashMap<InputParams, CalculationResult>();

    @Override
    public CalculationResult getCalculationResult (InputParams pair)
    {
        CalculationResult result = theMap.get(pair);
        if (result!= null)
        {
            logger.info("Returning Found Result from Cache");
        }
        return result;
    }
    @Override
    public void addCalculationresult(InputParams pair, CalculationResult result)
    {
        logger.info("Adding result to Cache:", pair, " Is ",result);
        theMap.put(pair, result);
    }




}