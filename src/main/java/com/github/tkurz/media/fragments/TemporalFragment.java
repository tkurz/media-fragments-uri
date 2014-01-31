package com.github.tkurz.media.fragments;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalFragment<T> {

    /**
     * temporal units; at the moment only npt is supported
     */
    public enum Unit {
        NPT,
        SMPTE,
        SMPTE_25,
        SMPTE_30,
        SMPTE_30_DROP,
        CLOCK;

        public static Unit fromString(String s) {
            if(s==null) return NPT;
            return valueOf(s.replace("-","_").toUpperCase());
        }
    }

    public Unit getUnit();

    public boolean equal(T t);

    public boolean after(T t);

    public boolean before(T t);

    public boolean meets(T t);

    public boolean overlaps(T t);

    public boolean includes(T t);

    public boolean covers(T t);

    public TemporalFragment<T> getIntersection(T t);

    public TemporalFragment<T> getUnion(T t);

    public TemporalFragment<T> getIntermediate(T t);

    public TemporalFragment<T> getBoundingBox(T t);
}
