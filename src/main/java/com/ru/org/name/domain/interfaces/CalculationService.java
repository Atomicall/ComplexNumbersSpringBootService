package com.ru.org.name.domain.interfaces;

import com.ru.org.name.domain.InternalValidationExceptionsImpl;
import com.ru.org.name.models.CalculationResult;

public interface CalculationService { // это наш интерфейс для сервиса. Содержит только описание ф-й. Воплощаем их в CSImpl
    CalculationResult calculate(double re, double im) throws InternalValidationExceptionsImpl;
}
