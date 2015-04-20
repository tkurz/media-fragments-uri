package com.github.tkurz.media.fragments.temporal;

import com.github.tkurz.media.ontology.impl.Instant;

/**
 * Represents clocktime as double value
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class Clocktime extends Instant {

    public static final Clocktime ZERO;
    public static final Clocktime INFINIT;

    static {
        ZERO = new Clocktime(0);
        INFINIT = new Clocktime(Double.POSITIVE_INFINITY);
    }

    public Clocktime(double d) {
        super(d);
    }

    /**
     * parse clocktime in seconds and milliseconds (e.g. 123.456)
     * @param seconds
     */
    public Clocktime(String seconds) {
        super(Double.parseDouble(seconds));
    }

    /**
     * parse clocktime given by hours, minutes, seconds and milliseconds
     * @param hour
     * @param minutes
     * @param seconds
     * @param ms
     */
    public Clocktime(String hour, String minutes, String seconds, String ms) {
        super(parseClocktime(hour, minutes, seconds, ms));
    }

    private static double parseClocktime(String hour, String minutes, String seconds, String ms) {
        double h = hour != null ? Double.parseDouble(hour) * 3600 : 0;
        double m = Double.parseDouble(minutes) * 60;
        double s = Double.parseDouble(seconds);
        double z = ms != null ? Double.parseDouble("0."+ms) : 0;
        return h+m+s+z;
    }

    public static Clocktime max(Clocktime... clocktimes) {
        Clocktime max = clocktimes[0];
        for(Clocktime c : clocktimes) {
            max = max.compareTo(c) < 0 ? c : max;
        }
        return max;
    }

    public static Clocktime min(Clocktime... clocktimes) {
        Clocktime min = clocktimes[0];
        for(Clocktime c : clocktimes) {
            min = min.compareTo(c) < 0 ? min : c;
        }
        return min;
    }
}
