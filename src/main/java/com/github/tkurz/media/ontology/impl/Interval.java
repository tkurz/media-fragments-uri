package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.type.TemporalEntity;
import com.github.tkurz.media.ontology.type.Time;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Interval implements TemporalEntity {

    Time t1,t2;

    public Interval(Double start, Double end) {
        this.t1 = start == null ? null : new Instant(start);
        this.t2 = end == null ? null : new Instant(end);
    }

    public Interval(Time t1, Time t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public Time getStart() {
        return t1;
    }

    @Override
    public Time getEnd() {
        return t2;
    }
}
