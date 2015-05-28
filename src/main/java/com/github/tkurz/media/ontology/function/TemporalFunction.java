package com.github.tkurz.media.ontology.function;

import com.github.tkurz.media.ontology.exception.NotAggregatableException;
import com.github.tkurz.media.ontology.exception.NotComparableException;
import com.github.tkurz.media.ontology.impl.Interval;
import com.github.tkurz.media.ontology.type.TemporalEntity;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class TemporalFunction {

    public static boolean precedes(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getStart()) < 0;
    }

    public static boolean precededBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getEnd()) > 0;
    }

    public static boolean meets(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getStart()) == 0;
    }

    public static boolean metBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return meets(e2,e1);
    }

    public static boolean overlaps(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getStart()) < 0 && e1.getEnd().compareTo(e2.getEnd()) < 0 && e2.getStart().compareTo(e1.getEnd()) < 0;
    }

    public static boolean overlappedBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return overlaps(e2,e1);
    }

    public static boolean during(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e2.getStart().compareTo(e1.getStart()) < 0 && e1.getEnd().compareTo(e2.getEnd()) < 0;
    }

    public static boolean contains(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getStart()) < 0 && e2.getEnd().compareTo(e1.getEnd()) < 0;
    }

    public static boolean finishes(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getEnd()) == 0 && e1.getStart().compareTo(e2.getStart()) > 0;
    }

    public static boolean finishedBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return finishes(e2,e1);
    }

    public static boolean starts(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getStart()) == 0 && e1.getEnd().compareTo(e2.getEnd()) < 0;
    }

    public static boolean startedBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return starts(e2,e1);
    }

    public static boolean equals(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getStart()) == 0 && e1.getEnd().compareTo(e2.getEnd()) == 0;
    }

    public static Interval getBoundingBox(TemporalEntity... entities) {
        double start=Double.MAX_VALUE,end=0;
        for(TemporalEntity e : entities) {
            start = Math.min(e.getStart().getValue(),start);
            end = Math.max(e.getEnd().getValue(),end);
        }
        return new Interval(start,end);
    }

    public static Interval getIntermediate(TemporalEntity e1, TemporalEntity e2) throws NotAggregatableException {
        try {
            if(precededBy(e1, e2)) {
                return new Interval(
                        e2.getEnd().getValue(),
                        e1.getStart().getValue()
                );
            } else if(precededBy(e2, e1)) {
                return new Interval(
                        e1.getEnd().getValue(),
                        e2.getStart().getValue()
                );
            } else throw new NotAggregatableException();
        } catch (NotComparableException e) {
            throw new NotAggregatableException();
        }
    }

    public static Interval getIntersect(TemporalEntity e1, TemporalEntity e2) throws NotAggregatableException {
        try {
            if(overlaps(e1,e2)||overlappedBy(e1,e2)) {
                return new Interval(
                        Math.max(e1.getStart().getValue(), e2.getStart().getValue()),
                        Math.min(e1.getEnd().getValue(), e2.getEnd().getValue())
                );
            } else throw new NotAggregatableException();
        } catch (NotComparableException e) {
            throw new NotAggregatableException();
        }
    }
}
