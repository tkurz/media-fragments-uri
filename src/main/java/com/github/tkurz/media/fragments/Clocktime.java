package com.github.tkurz.media.fragments;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Clocktime {

    public static final Clocktime ZERO;
    public static final Clocktime INFINIT;

    static {
        ZERO = new Clocktime(0);
        INFINIT = new Clocktime(Double.POSITIVE_INFINITY);
    }

    private double value;

    public Clocktime(double d) {
        this.value = d;
    }

    public Clocktime(String seconds) {
        value = Double.parseDouble(seconds);
    }

    public Clocktime(String hour, String minutes, String seconds, String ms) {
        double h = hour != null ? Double.parseDouble(hour) * 3600 : 0;
        double m = Double.parseDouble(minutes) * 60;
        double s = Double.parseDouble(seconds);
        double z = ms != null ? Double.parseDouble("0."+ms) : 0;
        value = h+m+s+z;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

}
