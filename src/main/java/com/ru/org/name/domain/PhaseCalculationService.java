package com.ru.org.name.domain;

import com.ru.org.name.interfaces.CalculationService;
import com.ru.org.name.models.CalculationResult;

public class PhaseCalculationService implements CalculationService { // Интерфейс лдя сервима расчета Фазы. Но Зачем...
    @Override
    public CalculationResult calculate(double re, double im) {
        return null;
    }
}
