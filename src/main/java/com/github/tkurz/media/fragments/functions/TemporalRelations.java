package com.github.tkurz.media.fragments.functions;

/**
 * based on Allen, J.F.: Maintaining Knowledge about Temporal Intervals
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalRelations<T> {

    public boolean before(T s1);
    public boolean meets(T s1);
    public boolean overlaps(T s1);
    public boolean starts(T s1);
    public boolean finishes(T s1);
    public boolean contains(T s1);
    public boolean after(T s1);
    public boolean equal(T s1);

}
