package com.github.tkurz.media.ontology.type;

import com.github.tkurz.media.ontology.impl.Rectangle;

import java.awt.geom.Area;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface SpatialEntity {

    public Coordinate getCenter();

    public Rectangle getBoundingBox();

    public Area getArea();
}
