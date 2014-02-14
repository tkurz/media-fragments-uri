package com.github.tkurz.media.fragments.functions;

import com.github.tkurz.media.fragments.FunctionException;
import com.github.tkurz.media.fragments.SpatialFragment;

/**
 * Based on the Dimensionally Extended nine-Intersection Model (DE-9IM)
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TopologicalRelations {

    public boolean equal(SpatialFragment s) throws FunctionException ;//  a = b
    public boolean disjoint(SpatialFragment s) throws FunctionException;//  no common point a n b = null
    public boolean intersects(SpatialFragment s) throws FunctionException ;//  a n b != null
    public boolean touches(SpatialFragment s) throws FunctionException ;//  eh klar
    public boolean covers(SpatialFragment s) throws FunctionException ;//  is more inclusive as contains but not necessary for rectangles and intervals

}
