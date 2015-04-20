package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.exception.NotComparableRuntimeException;
import com.github.tkurz.media.ontology.type.TemporalEntity;
import com.github.tkurz.media.ontology.type.Time;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Instant implements TemporalEntity, Time {

    private double value;

    public Instant(double value) {
        this.value = value;
    }

    @Override
    public Time getStart() {
        return this;
    }

    @Override
    public Time getEnd() {
        return this;
    }

    @Override
    public int compareTo(Time o) {
        if(o instanceof Instant) return Double.compare(value, ((Instant)o).value);
        throw new NotComparableRuntimeException();
    }

    public String toString() {
        if((int) value == value) return Integer.toString((int) value);
        return String.valueOf(value);
    }

    public double getValue() {
        return value;
    }
}
