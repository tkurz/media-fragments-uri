package com.github.tkurz.media.fragments;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragment {

    private String id;
    private String track;
    private TemporalFragment temporalFragment;
    private RegionalFragment regionalFragment;

    public boolean hasId() {return id!=null;}

    public boolean hasTrack() {return track!=null;}

    public boolean hasTemporalFragment() {return temporalFragment != null;}

    public boolean hasRegionalFragment() {return regionalFragment != null;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public TemporalFragment getTemporalFragment() {
        return temporalFragment;
    }

    public void setTemporalFragment(TemporalFragment temporalFragment) {
        this.temporalFragment = temporalFragment;
    }

    public RegionalFragment getRegionalFragment() {
        return regionalFragment;
    }

    public void setRegionalFragment(RegionalFragment regionalFragment) {
        this.regionalFragment = regionalFragment;
    }

    public String toString() {
        if(id!=null) return "#id="+id;
        Set<String> set = new HashSet<>();
        if(track!=null) set.add("track="+track);
        if(temporalFragment!=null) set.add(temporalFragment.toString());
        if(regionalFragment!=null) set.add(regionalFragment.toString());
        return join(set);
    }

    private String join(Set set) {
        StringBuilder b = new StringBuilder();
        Iterator<Set> iterator = set.iterator();
        if(iterator.hasNext()) b.append("#");
        while(iterator.hasNext()) {
            b.append(iterator.next());
            if(iterator.hasNext()) b.append("&");
        }
        return b.toString();
    }
}
