package com.ru.org.name;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.ru.org.name.domain.CalculationServiceImpl;
import com.ru.org.name.domain.InternalValidationExceptionsImpl;
import com.ru.org.name.models.CalculationResult;
import org.junit.jupiter.api.Test;





public class CalculationServiceImplTest {

    CalculationServiceImpl service = new CalculationServiceImpl();
    @Test
    void testReturnsCorrectValue() throws InternalValidationExceptionsImpl {
        assertEquals(service.calculate(1, 3), new CalculationResult(1.2490457723982544, 3.1622776601683795));
    }


}