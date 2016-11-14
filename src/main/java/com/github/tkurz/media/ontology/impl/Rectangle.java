package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.type.Coordinate;
import com.github.tkurz.media.ontology.type.SpatialEntity;
import com.github.tkurz.media.ontology.utils.Utils;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Rectangle extends Rectangle2D.Double implements SpatialEntity {

    public Rectangle(Rectangle2D r) {
        super(r.getX(),r.getY(),r.getWidth(),r.getHeight());
    }

    public Rectangle(Coordinate a, Coordinate b) {
        super(a.getX(),a.getY(),b.getX()-a.getX(),b.getY()-a.getY());
    }

    public Rectangle(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    @Override
    public Coordinate getCenter() {
        return new Point(this.getCenterX(),this.getCenterY());
    }

    @Override
    public Rectangle getBoundingBox() {
        return this;
    }

    public Coordinate getUpperLeft() {
        return new Point(x,y);
    }

    public Coordinate getLowerRight() {
        return new Point(x+width,y+height);
    }

    @Override
    public Area getArea() {
        return new Area(this);
    }

    @Override
    public String stringValue() {
        return "xywh=" + Utils.prettyPrint(x) + "," + Utils.prettyPrint(y) + "," + Utils.prettyPrint(width) + "," + Utils.prettyPrint(height);
    }

    @Override
    public String stringValue(Format format) {
        return stringValue(); //TODO should support more types
    }

}
