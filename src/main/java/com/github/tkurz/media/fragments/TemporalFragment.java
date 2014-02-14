package com.github.tkurz.media.fragments;

import com.github.tkurz.media.fragments.functions.TemporalFunctions;
import com.github.tkurz.media.fragments.functions.TemporalRelations;

/**
 * TODO: use standard for temporal relations http://web.cacs.louisiana.edu/~logan/521_f08/Doc/p832-allen.pdf
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalFragment<T> extends TemporalRelations<T>, TemporalFunctions<T> {

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

    public Unit getUnit();
}
