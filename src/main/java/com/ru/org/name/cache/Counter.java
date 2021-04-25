package com.ru.org.name.cache;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private static AtomicInteger calcsCounter = new AtomicInteger(); // Считаем кол-во подсчетов
    public static Integer getCounter() {return calcsCounter.get();}
    public static void increaseCounter(){calcsCounter.incrementAndGet();}
}
