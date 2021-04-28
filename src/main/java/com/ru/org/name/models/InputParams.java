package com.ru.org.name.models;

import java.util.Objects;

public class InputParams {
    private double real;
    private double imagine;


    public InputParams(double real, double imagine)
    {
        this.real = real;
        this.imagine = imagine;
    }

    public double getReal() {
        return real;
    }

    public double getImagine() {
        return imagine;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImagine(double imagine) {
        this.imagine = imagine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParams that = (InputParams) o;
        return Double.compare(that.real, real) == 0 && Double.compare(that.imagine, imagine) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imagine);
    }

}
