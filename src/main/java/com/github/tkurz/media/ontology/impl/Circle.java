package com.github.tkurz.media.ontology.impl;

import com.github.tkurz.media.ontology.type.Coordinate;
import com.github.tkurz.media.ontology.type.SpatialEntity;
import com.github.tkurz.media.ontology.utils.Utils;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Circle extends Ellipse2D.Double implements SpatialEntity {

    public Circle(Point point, int radius) {
        super(point.getX()-radius,point.getY()-radius,2*radius,2*radius);
    }

    public Circle(int x, int y, int radius) {
        super(x-radius,y-radius,2*radius,2*radius);
    }

    @Override
    public Coordinate getCenter() {
        return new Point(getCenterX(),getCenterY());
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(getBounds2D());
    }

    @Override
    public Area getArea() {
        return new Area(this);
    }

    @Override
    public String stringValue() {
        return "xcr="+ Utils.prettyPrint(getCenterX())+","+Utils.prettyPrint(getCenterY())+","+Utils.prettyPrint(width/2);
    }

    @Override
    public String stringValue(Format format) {
        return stringValue(); //TODO should support more types
    }
}
