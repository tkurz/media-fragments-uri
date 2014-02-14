package com.github.tkurz.media.fragments;

/**
 * Represents a temporal fragment in 'Normal Play Time' (npt) like specified in RFC 2326.
 * By default, start time is 0.0 and end time is infinite.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class NPTFragment implements TemporalFragment<NPTFragment> {

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

    public String toString() {
        if(end == Clocktime.INFINIT) return "t="+start;
        if(start == Clocktime.ZERO) return "t=,"+end;
        return "t="+start+","+end;
    }

    @Override
    public boolean equal(NPTFragment f) {
        return this.start.compareTo(f.start) == 0 && this.end.compareTo(f.end) == 0;
    }

    @Override
    public boolean after(NPTFragment f) {
        return this.start.compareTo(f.end) >= 0;
    }

    @Override
    public boolean before(NPTFragment f) {
        return this.end.compareTo(f.start) <= 0;
    }

    @Override
    public boolean meets(NPTFragment f) {
        return this.start.compareTo(f.end) == 0 || this.end.compareTo(f.start) == 0;
    }

    @Override
    public boolean overlaps(NPTFragment f) {
        return this.start.compareTo(f.end) == -1  && f.end.compareTo(this.end) == -1
                || f.start.compareTo(this.end) == -1  && this.end.compareTo(f.end) == -1
                || this.contains(f)
                || f.contains(this);
    }

    @Override
    public boolean starts(NPTFragment s1) {
        return this.start.compareTo(s1.start) == 0 && this.end.compareTo(s1.end) <= 0;
    }

    @Override
    public boolean finishes(NPTFragment s1) {
        return this.start.compareTo(s1.start) >= 0 && this.end.compareTo(s1.end) == 0;
    }

    @Override
    public boolean contains(NPTFragment s1) {
        return this.start.compareTo(s1.start) <= 0 && this.end.compareTo(s1.end) >= 0;
    }

    @Override
    public TemporalFragment<NPTFragment> getIntersection(NPTFragment f) {
        if(this.overlaps(f)) {
            return new NPTFragment(Clocktime.max(this.start,f.start),Clocktime.min(this.end,f.end));
        }
        return null;
    }

    @Override
    public TemporalFragment<NPTFragment> getUnion(NPTFragment f) {
        if(this.overlaps(f)) {
            return this.getBoundingBox(f);
        }
        return null;
    }

    @Override
    public TemporalFragment<NPTFragment> getIntermediate(NPTFragment f) {
        if(this.overlaps(f)) return null;

        return new NPTFragment(Clocktime.min(this.end,f.end),Clocktime.max(this.start,f.start));
    }

    @Override
    public TemporalFragment<NPTFragment> getBoundingBox(NPTFragment f) {
        return new NPTFragment(Clocktime.min(this.start,f.start),Clocktime.max(this.end,f.end));
    }


}
