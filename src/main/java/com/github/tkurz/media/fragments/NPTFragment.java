package com.github.tkurz.media.fragments;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class NPTFragment implements TemporalFragment {

    private Clocktime start;
    private Clocktime end;

    public NPTFragment() {
        this.start = Clocktime.ZERO;
        this.end = Clocktime.INFINIT;
    }

    public NPTFragment(Clocktime start, Clocktime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Unit getUnit() {
        return Unit.NPT;
    }

    public Clocktime getStart() {
        return start != null ? start : Clocktime.ZERO;
    }

    public void setStart(Clocktime start) {
        this.start = start;
    }

    public Clocktime getEnd() {
        return end != null ? end : Clocktime.INFINIT;
    }

    public void setEnd(Clocktime end) {
        this.end = end;
    }

    public String toString() {
        if(end == Clocktime.INFINIT) return "t="+start;
        if(start == Clocktime.ZERO) return "t=,"+end;
        return "t="+start+","+end;
    }
}
