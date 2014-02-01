package com.github.tkurz.media.fragments;

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

    public boolean leftBeside(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return this.getX()+this.getW() <= r.getX();
    }

    public boolean rightBeside(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return this.getX() >= r.getX()+r.getW();
    }

    public boolean isAbove(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return this.getY()+this.getH() <= r.getY();
    }

    public boolean isBelow(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return this.getY() >= r.getY()+r.getH();
    }

    public boolean top() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getY()+this.getH() <= 50;
        else throw new FunctionException("top is not supported for unit pixel");
    }

    public boolean bottom() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getY() >= 50;
        else throw new FunctionException("bottom is not supported for unit pixel");
    }

    public boolean left() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getX()+this.getW() <= 50;
        else throw new FunctionException("left is not supported for unit pixel");
    }

    public boolean right() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getX() >= 50;
        else throw new FunctionException("right is not supported for unit pixel");
    }

    //not (X1+W1<X2 or X2+W2<X1 or Y1+H1<Y2 or Y2+H2<Y1)
    public boolean overlaps(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return !(this.getX()+this.getW() <= r.getX()
            || r.getX()+r.getW() <= this.getX()
            || this.getY()+this.getH() <= r.getY()
            || r.getY()+r.getH() <= this.getY()
        );
    }

    public boolean covers(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return (this.getX() <= r.getX()
            && this.getY() <= r.getY()
            && this.getX() + this.getW() >= r.getX() + r.getW()
            && this.getY() + this.getH() >= r.getY() + r.getH()
        );
    }

    public boolean disjoint(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return !this.overlaps(r);
    }

    public SpatialFragment getIntersection(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        if(!this.overlaps(r)) return null;
        return new SpatialFragment(this.getUnit(),
                Math.max(this.getX(),r.getX()),
                Math.max(this.getY(), r.getY()),
                Math.min(this.getX() + this.getW(), r.getX() + r.getW()) - Math.max(this.getX(),r.getX()),
                Math.min(this.getY() + this.getH(), r.getY() + r.getH()) - Math.max(this.getY(), r.getY()));
    }

    public SpatialFragment getBoundingBox(SpatialFragment r) throws FunctionException {
        assertComparable(this, r);
        return new SpatialFragment(this.getUnit(),
                Math.min(this.getX(), r.getX()),
                Math.min(this.getY(), r.getY()),
                Math.max(this.getX() + this.getW(), r.getX() + r.getW()),
                Math.max(this.getY() + this.getH(), r.getY() + r.getH()));
    }

    private boolean assertComparable(SpatialFragment r1, SpatialFragment r2) throws FunctionException {
        if(r1.getUnit() == r2.getUnit()) return true;
        else throw new FunctionException("spatial fragments are not comparable");
    }
}
