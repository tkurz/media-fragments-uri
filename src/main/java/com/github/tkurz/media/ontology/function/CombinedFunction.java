package com.github.tkurz.media.ontology.function;

import com.github.tkurz.media.ontology.exception.NotAggregatableException;
import com.github.tkurz.media.ontology.exception.NotComparableException;
import com.github.tkurz.media.ontology.type.SpatialTemporalEntity;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class CombinedFunction {

    private boolean contains(SpatialTemporalEntity e1, SpatialTemporalEntity e2) throws NotComparableException {
        return false;
    }

    private SpatialTemporalEntity boundingBox(SpatialTemporalEntity e1, SpatialTemporalEntity e2) throws NotAggregatableException {
        return null;
    }

    public static SpatialTemporalEntity intersection(SpatialTemporalEntity e1, SpatialTemporalEntity e2) throws NotAggregatableException {
        return null;
    }

}
