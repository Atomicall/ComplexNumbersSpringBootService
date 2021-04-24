package com.ru.org.name;

import com.ru.org.name.models.CalculationResult;

public interface CalculationService { // это наш интерфейс для сервиса. Содержит только описание ф-й. Воплощаем их в CSImpl
    CalculationResult calculate(double re, double im);
}
