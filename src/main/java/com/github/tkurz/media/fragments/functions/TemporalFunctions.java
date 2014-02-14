package com.github.tkurz.media.fragments.functions;

import com.github.tkurz.media.fragments.temporal.TemporalFragment;

/**
 * Temporal functions create new temporal fragments out from existing ones
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalFunctions<T> {

    public TemporalFragment<T> getIntersection(T t);

    public TemporalFragment<T> getUnion(T t);

    public TemporalFragment<T> getIntermediate(T t);

    public TemporalFragment<T> getBoundingBox(T t);

}
