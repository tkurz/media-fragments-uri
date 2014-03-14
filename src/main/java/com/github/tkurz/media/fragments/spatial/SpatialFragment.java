package com.github.tkurz.media.fragments.spatial;

import com.github.tkurz.media.fragments.exceptions.FunctionException;
import com.github.tkurz.media.fragments.functions.DirectionalRelations;
import com.github.tkurz.media.fragments.functions.SpatialFunctions;
import com.github.tkurz.media.fragments.functions.TopologicalRelations;

/**
 * Represents a spacial fragment. The default unit is 'pixel', the default values for x,y,w,h are 0.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFragment implements TopologicalRelations, DirectionalRelations, SpatialFunctions {

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

    /**
     * tests spatial relation
     * @param spatialFragment a spatial fragment
     * @return <code>true</code>, if this object <code>A</code> is left beside <code>spatialFragment B</code> (A.rightBound left_beside B.leftBound),
     *         <code>false</code> otherwise
     * @throws com.github.tkurz.media.fragments.exceptions.FunctionException
     */
    @Override
    public boolean leftBeside(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return this.getX()+this.getW() <= spatialFragment.getX();
    }

    /**
     * tests spatial relation
     * @param spatialFragment a spatial fragment
     * @return <code>true</code>, if this object <code>A</code> is right beside <code>spatialFragment B</code> (A.leftBound right_beside B.rightBound),
     *         <code>false</code> otherwise
     * @throws FunctionException
     */
    @Override
    public boolean rightBeside(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return this.getX() >= spatialFragment.getX()+ spatialFragment.getW();
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object <code>A</code> is above <code>spatialFragment B</code> (A.lowerBound is_above B.upperBound),
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    @Override
    public boolean isAbove(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return this.getY()+this.getH() <= spatialFragment.getY();
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object <code>A</code> is below <code>spatialFragment B</code> (A.lowerBound is_below B.upperBound),
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    @Override
    public boolean isBelow(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return this.getY() >= spatialFragment.getY() + spatialFragment.getH();
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object is within the top section (lower bound above 50%) of the related media resource,
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code> is <code>Unit.PERCENTAGE</code>)
     */
    @Override
    public boolean top() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getY()+this.getH() <= 50;
        else throw new FunctionException("top is not supported for unit pixel");
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object is within the bottom section (upper bound below 50%) of the related media resource,
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code> is <code>Unit.PERCENTAGE</code>)
     */
    @Override
    public boolean bottom() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getY() >= 50;
        else throw new FunctionException("bottom is not supported for unit pixel");
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object is within the left section (right bound left beside 50%) of the related media resource,
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code> is <code>Unit.PERCENTAGE</code>)
     */
    @Override
    public boolean left() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getX()+this.getW() <= 50;
        else throw new FunctionException("left is not supported for unit pixel");
    }

    /**
     * tests spatial relation
     * @return <code>true</code>, if this object is within the left right (left bound right beside 50%) of the related media resource,
     *         <code>false</code> otherwise
     * @throws FunctionException if the function is not supported (<code>unit</code> is <code>Unit.PERCENTAGE</code>)
     */
    @Override
    public boolean right() throws FunctionException {
        if(this.unit == Unit.PERCENT) return this.getX() >= 50;
        else throw new FunctionException("right is not supported for unit pixel");
    }

    /**
     * tests spatial relation
     * @param spatialFragment a spacialFragment this object should be compared with
     * @return the result of the following formula:</br>given this object <code>A</code> and <code>spatialFragment B</code></br>
     *  not ( ( A.x + A.w <= B.x ) or ( B.x + B.w <= A.x ) or ( A.y + A.h <= B.y  ) or (B.y + B.h <= A.y ) )
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    @Override
    public boolean intersects(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return !(this.getX()+this.getW() <= spatialFragment.getX()
            || spatialFragment.getX() + spatialFragment.getW() <= this.getX()
            || this.getY()+this.getH() <= spatialFragment.getY()
            || spatialFragment.getY() + spatialFragment.getH() <= this.getY()
        );
    }

    @Override
    public boolean touches(SpatialFragment s) {
        return (this.getX()+ this.getW() == s.getX()
            || this.getY() + this.getH() == s.getY()
            || this.getX() == s.getX() + s.getW()
            || this.getY() == s.getY() + s.getH()
        );
    }

    /**
     * tests spatial relation
     * @param spatialFragment a spacialFragment this object should be compared with
     * @return the result of the following formula:</br>given this object <code>A</code> and <code>spatialFragment B</code></br>
     * ( A.x <= B.x ) and ( A.y <= B.y ) and ( A.x + A.w <= B.x + B.w ) and ( A.x + A.w <= B.x + B.w )
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    @Override
    public boolean covers(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return (this.getX() <= spatialFragment.getX()
            && this.getY() <= spatialFragment.getY()
            && this.getX() + this.getW() >= spatialFragment.getX() + spatialFragment.getW()
            && this.getY() + this.getH() >= spatialFragment.getY() + spatialFragment.getH()
        );
    }

    @Override
    public boolean equal(SpatialFragment s) {
        return (this.getX() == s.getX()
            && this.getY() == s.getY()
            && this.getW() == s.getW()
            && this.getH() == s.getH()
        );
    }

    /**
     * tests spatial relation
     * @param spatialFragment a spacialFragment this object should be compared with
     * @param spatialFragment a spacialFragment this object should be compared with
     * @return the opposite of {@link #intersects(SpatialFragment)}
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    public boolean disjoint(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return !this.intersects(spatialFragment);
    }

    /**
     * aggregates spacial fragments
     * @param spatialFragment a spacialFragment this object should be aggregated with
     * @return if fragments does not overlap, return <code>null</code>, otherwise return a spatial fragment of this object <code>A</code> and the <code>spatialFragment B</code> like this:</br>
     * <ul>
     *     <li><em>x</em> = max( A.x, B.x )</li>
     *     <li><em>y</em> = max( A.y, B.y )</li>
     *     <li><em>w</em> = min( A.x + A.w, B.x + B.w ) - max( A.x, B.x )</li>
     *     <li><em>h</em> = min( A.y + A.h, B.y + B.h ) - max( A.y, A.x )</li>
     * </ul>
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    public SpatialFragment getIntersection(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        if(!this.intersects(spatialFragment)) return null;
        return new SpatialFragment(this.getUnit(),
                Math.max(this.getX(), spatialFragment.getX()),
                Math.max(this.getY(), spatialFragment.getY()),
                Math.min(this.getX() + this.getW(), spatialFragment.getX() + spatialFragment.getW()) - Math.max(this.getX(), spatialFragment.getX()),
                Math.min(this.getY() + this.getH(), spatialFragment.getY() + spatialFragment.getH()) - Math.max(this.getY(), spatialFragment.getY()));
    }

    /**
     * aggregates spacial fragments
     * @param spatialFragment a spacialFragment this object should be aggregated with
     * @return if fragments does not overlap, return <code>null</code>, otherwise return a spatial fragment of this object <code>A</code> and the <code>spatialFragment B</code> like this:</br>
     * <ul>
     *     <li><em>x</em> = min( A.x, B.x )</li>
     *     <li><em>y</em> = min( A.y, B.y )</li>
     *     <li><em>w</em> = max( A.x + A.w, B.x + B.w )</li>
     *     <li><em>h</em> = max( A.y + A.h, B.y + B.h )</li>
     * </ul>
     * @throws FunctionException if the function is not supported (<code>unit</code>s are not comparable)
     */
    public SpatialFragment getBoundingBox(SpatialFragment spatialFragment) throws FunctionException {
        assertComparable(this, spatialFragment);
        return new SpatialFragment(this.getUnit(),
                Math.min(this.getX(), spatialFragment.getX()),
                Math.min(this.getY(), spatialFragment.getY()),
                Math.max(this.getX() + this.getW(), spatialFragment.getX() + spatialFragment.getW()) - Math.min(this.getX(), spatialFragment.getX()),
                Math.max(this.getY() + this.getH(), spatialFragment.getY() + spatialFragment.getH()) - Math.min(this.getY(), spatialFragment.getY()));
    }

    private boolean assertComparable(SpatialFragment spatialFragment1, SpatialFragment spatialFragment2) throws FunctionException {
        if(spatialFragment1.getUnit() == spatialFragment2.getUnit()) return true;
        else throw new FunctionException("spatial fragments are not comparable");
    }
}
