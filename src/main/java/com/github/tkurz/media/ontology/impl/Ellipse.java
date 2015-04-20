package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.type.Coordinate;
import com.github.tkurz.media.ontology.type.SpatialEntity;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Ellipse extends Ellipse2D.Double implements SpatialEntity {

    public Ellipse(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public Coordinate getCenter() {
        return new Point(x+width/2,y+height/2);
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x,y,width,height);
    }

    @Override
    public Area getArea() {
        return new Area(this);
    }
}
