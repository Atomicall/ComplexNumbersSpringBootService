package com.ru.org.name.data;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Counter {
    // А нужно ли это все делать как статик при том , что это бин (component)
    private static AtomicInteger calculationsCounter = new AtomicInteger(); // Считаем кол-во подсчетов
    public static Integer getCounter() {return calculationsCounter.get();}
    public static void increaseCounter(){calculationsCounter.incrementAndGet();}
}

