package com.ru.org.name.models;

import java.util.Objects;

public class CalculationResult {
    private double phase;

    private double module;

    public CalculationResult(double phase, double module) {
        this.phase = phase;
        this.module = module;
    }

    public double getPhase() {
        return phase;
    }

    public double getModule() {
        return module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResult that = (CalculationResult) o;
        return Double.compare(that.phase, phase) == 0 && Double.compare(that.module, module) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phase, module);
    }
}
