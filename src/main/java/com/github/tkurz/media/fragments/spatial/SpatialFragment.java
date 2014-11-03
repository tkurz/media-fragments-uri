package com.github.tkurz.media.fragments.spatial;

/**
 * Represents a spacial fragment. The default unit is 'pixel', the default values for x,y,w,h are 0.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFragment {

    private Unit unit = Unit.PIXEL;
    private double x,y,w,h;

    public SpatialFragment() {}

    public SpatialFragment(double x, double y, double w, double h) {
        this(Unit.PIXEL,x,y,w,h);
    }

    public SpatialFragment(Unit unit, double x, double y, double w, double h) {
        this.unit = unit;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public static enum Unit {
        PIXEL, PERCENT
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("xywh=");
        b.append(unitToString(unit));
        b.append(doubleToString(x));
        b.append(",");
        b.append(doubleToString(y));
        b.append(",");
        b.append(doubleToString(w));
        b.append(",");
        b.append(doubleToString(h));
        return b.toString();
    }

    private String doubleToString(double d) {
        if((int) d == d) return Integer.toString((int) d);
        return String.valueOf(d);
    }

    private String unitToString(Unit u) {
        return u == Unit.PIXEL ? "" : u.name().toLowerCase()+":";
    }

}
