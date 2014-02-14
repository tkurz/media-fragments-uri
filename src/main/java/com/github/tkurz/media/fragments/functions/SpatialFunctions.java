package com.github.tkurz.media.fragments.functions;

import com.github.tkurz.media.fragments.exceptions.FunctionException;
import com.github.tkurz.media.fragments.spatial.SpatialFragment;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface SpatialFunctions {

    public SpatialFragment getIntersection(SpatialFragment spatialFragment) throws FunctionException;
    public SpatialFragment getBoundingBox(SpatialFragment spatialFragment) throws FunctionException;

}
