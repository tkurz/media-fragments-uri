package com.github.tkurz.media.fragments.functions;

import com.github.tkurz.media.fragments.exceptions.FunctionException;
import com.github.tkurz.media.fragments.spatial.SpatialFragment;

/**
 * Allows testing directional relations of one fragment regarding the overall asset or between two fragments
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface DirectionalRelations {

    public boolean leftBeside(SpatialFragment s) throws FunctionException ;
    public boolean rightBeside(SpatialFragment s) throws FunctionException ;
    public boolean isAbove(SpatialFragment s) throws FunctionException ;
    public boolean isBelow(SpatialFragment s) throws FunctionException ;

    public boolean top() throws FunctionException;
    public boolean left() throws FunctionException ;
    public boolean bottom() throws FunctionException ;
    public boolean right() throws FunctionException ;

}
