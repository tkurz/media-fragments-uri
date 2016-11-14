package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.type.Coordinate;
import com.github.tkurz.media.ontology.type.SpatialEntity;
import com.github.tkurz.media.ontology.utils.Utils;

import java.awt.geom.Area;
import java.awt.geom.Point2D;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Point extends Point2D.Double implements SpatialEntity, Coordinate {

    public static Point relativePoint(Point p1, Point p2) {
        return new Point(p2.x-p1.x,p2.y-p1.y);
    }

    public Point(double x, double y) {
        super(x,y);
    }

    @Override
    public String toString() {
        return doubleToString(x)+","+doubleToString(y);
    }

    private String doubleToString(double d) {
        if((int) d == d) return Integer.toString((int) d);
        return String.valueOf(d);
    }

    @Override
    public Coordinate getCenter() {
        return this;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(this,this);
    }

    @Override
    public Area getArea() {
        return new Area(new Rectangle(this,this));
    }

    @Override
    public String stringValue() {
        return Utils.prettyPrint(x) + "," + Utils.prettyPrint(y);
    }

    @Override
    public String stringValue(Format format) {
        return stringValue(); //TODO should support more types
    }
}
