package com.github.tkurz.media.fragments.base;

import com.github.tkurz.media.fragments.FragmentParser;
import com.github.tkurz.media.fragments.ParseException;
import com.github.tkurz.media.fragments.spatial.SpatialFragment;
import com.github.tkurz.media.fragments.temporal.TemporalFragment;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a media fragment. It can include id, track, temporalFragment and spatialFragment.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragment {

    public static final Type DEFAULT_TYPE = Type.FRAGMENT;

    public enum Type {
        FRAGMENT("#"), QUERY("?"), ;

        private String delimiter;

        Type(String delimiter) {
            this.delimiter = delimiter;
        }

        public String getDelimiter() {
            return delimiter;
        }
    }

    public Type type;
    private String id;
    private String track;
    private TemporalFragment temporalFragment;
    private SpatialFragment spatialFragment;

    public static MediaFragment create(String s) throws ParseException {
        Type _type = DEFAULT_TYPE;

        if(s.startsWith("?")) {
            _type = Type.QUERY;
            s = s.substring(1);
        }

        if(s.startsWith("#")) {
            _type = Type.FRAGMENT;
            s = s.substring(1);
        }

        FragmentParser p1 = new FragmentParser(new StringReader(s));
        return p1.run(_type);
    }

    public MediaFragment(){
        type = DEFAULT_TYPE;
    }

    public MediaFragment(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean hasId() {return id!=null;}

    public boolean hasTrack() {return track!=null;}

    public boolean hasTemporalFragment() {return temporalFragment != null;}

    public boolean hasSpatialFragment() {return spatialFragment != null;}

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

    public SpatialFragment getSpatialFragment() {
        return spatialFragment;
    }

    public void setSpatialFragment(SpatialFragment spatialFragment) {
        this.spatialFragment = spatialFragment;
    }

    public String toString() {
        String separator = type.getDelimiter();
        if(id!=null) return separator + "id="+id;
        Set<String> set = new HashSet<>();
        if(track!=null) set.add("track="+track);
        if(temporalFragment!=null) set.add(temporalFragment.toString());
        if(spatialFragment !=null) set.add(spatialFragment.toString());
        return join(set,separator);
    }

    private String join(Set set, String separator) {
        StringBuilder b = new StringBuilder();
        Iterator<Set> iterator = set.iterator();
        if(iterator.hasNext()) b.append(separator);
        while(iterator.hasNext()) {
            b.append(iterator.next());
            if(iterator.hasNext()) b.append("&");
        }
        return b.toString();
    }
}
