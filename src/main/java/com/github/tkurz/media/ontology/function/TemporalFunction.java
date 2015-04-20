package com.github.tkurz.media.ontology.function;

import com.github.tkurz.media.ontology.exception.NotComparableException;
import com.github.tkurz.media.ontology.type.TemporalEntity;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class TemporalFunction {

    public static boolean before(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getStart()) < 0;
    }

    public static boolean after(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getStart()) > 0;
    }

    public static boolean meets(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getEnd().compareTo(e2.getStart()) == 0;
    }

    public static boolean metBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return metBy(e1,e2);
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
        return e1.getEnd().compareTo(e2.getEnd()) == 0;
    }

    public static boolean finishedBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return finishes(e2,e1);
    }

    public static boolean starts(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return e1.getStart().compareTo(e2.getStart()) == 0;
    }

    public static boolean startedBy(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return starts(e2,e1);
    }

    public static boolean equals(TemporalEntity e1, TemporalEntity e2) throws NotComparableException {
        return starts(e1,e2) && finishes(e1,e2);
    }
}
