package com.github.tkurz.media.fragments;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalFragment {

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

    public Unit getUnit();

}
