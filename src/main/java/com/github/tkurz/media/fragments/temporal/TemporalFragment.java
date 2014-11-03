package com.github.tkurz.media.fragments.temporal;

/**
 * Basic interface for temporal fragments
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public abstract class TemporalFragment<T> {

    /**
     * temporal units; at the moment only npt is supported
     */
    public enum Unit {
        NPT,
        SMPTE,
        SMPTE_25,
        SMPTE_30,
        SMPTE_30_DROP,
        CLOCK;

        public static Unit fromString(String s) {
            if(s==null) return NPT;
            return valueOf(s.replace("-","_").toUpperCase());
        }
    }

    public abstract Unit getUnit();

}
