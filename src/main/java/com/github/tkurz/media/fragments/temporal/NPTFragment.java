package com.github.tkurz.media.fragments.temporal;

/**
 * Represents a temporal fragment in 'Normal Play Time' (npt) like specified in RFC 2326.
 * By default, start time is 0.0 and end time is infinite.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class NPTFragment extends TemporalFragment<NPTFragment> {

    private Clocktime start;
    private Clocktime end;

    public NPTFragment() {
        this.start = Clocktime.ZERO;
        this.end = Clocktime.INFINIT;
    }

    public NPTFragment(Clocktime start, Clocktime end) {
        this.start = start != null ? start : Clocktime.ZERO;
        this.end = end != null ? end : Clocktime.INFINIT;
    }

    @Override
    public Unit getUnit() {
        return Unit.NPT;
    }

    public Clocktime getStart() {
        return start;
    }

    public void setStart(Clocktime start) {
        this.start = start != null ? start : Clocktime.ZERO;
    }

    public Clocktime getEnd() {
        return end != null ? end : Clocktime.INFINIT;
    }

    public void setEnd(Clocktime end) {
        this.end = end != null ? end : Clocktime.INFINIT;
    }

    public String stringValue() {
        if(end == Clocktime.INFINIT) return "t="+start;
        if(start == Clocktime.ZERO) return "t=,"+end;
        return "t="+start+","+end;
    }

}
