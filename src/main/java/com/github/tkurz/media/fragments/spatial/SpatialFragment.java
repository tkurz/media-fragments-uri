package com.github.tkurz.media.fragments.spatial;

import com.github.tkurz.media.ontology.impl.Point;
import com.github.tkurz.media.ontology.impl.Rectangle;
import com.github.tkurz.media.ontology.utils.Utils;

/**
 * Represents a spacial fragment. The default unit is 'pixel', the default values for x,y,w,h are 0.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFragment extends Rectangle {

    private Unit unit = Unit.PIXEL;

    public SpatialFragment(double x, double y, double w, double h) {
        this(Unit.PIXEL,x,y,w,h);
    }

    public SpatialFragment(Unit unit, double x, double y, double w, double h) {
        super(x,y,w,h);
        this.unit = unit;
    }

    public static enum Unit {
        PIXEL, PERCENT;

        @Override
        public String toString() {
            return this == PIXEL ? "" : "percent:";
        }
    }

    public String stringValue() {
        return "xywh=" + unit + Utils.prettyPrint(x) + "," + Utils.prettyPrint(y) + "," + Utils.prettyPrint(width) + "," + Utils.prettyPrint(height);
    }

    @Override
    public String stringValue(Format format) {
        return stringValue(); //TODO should support more types
    }

    public SpatialFragment toPixel(double width, double height) {
        if(unit == Unit.PIXEL) return this; //TODO should be copied?

        return new SpatialFragment(Unit.PIXEL, x/(width/100), y/(height/100), this.width/(width/100), this.height/(height/100));
    }

    public SpatialFragment toPercent(double width, double height) {
        if(unit == Unit.PERCENT) return this; //TODO should be copied?

        return new SpatialFragment(Unit.PERCENT, x*(width/100), y*(height/100), this.width*(width/100), this.height*(height/100));
    }

}
