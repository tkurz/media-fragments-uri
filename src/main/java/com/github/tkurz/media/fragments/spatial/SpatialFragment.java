package com.github.tkurz.media.fragments.spatial;

import java.awt.geom.Rectangle2D;

/**
 * Represents a spacial fragment. The default unit is 'pixel', the default values for x,y,w,h are 0.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFragment extends Rectangle2D.Double {

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

    public String toString() {
        return "xywh=" + unit + toPrettyString(x) + "," + toPrettyString(y) + "," + toPrettyString(width) + "," + toPrettyString(height);
    }

    private String toPrettyString(double d) {
        return String.valueOf(d).replaceAll("\\.0$","");
    }

}
