package com.github.tkurz.media.fragments.base;

import com.github.tkurz.media.fragments.FragmentParser;
import com.github.tkurz.media.fragments.ParseException;
import com.github.tkurz.media.fragments.spatial.SpatialFragment;
import com.github.tkurz.media.fragments.temporal.TemporalFragment;
import com.github.tkurz.media.ontology.impl.Rectangle;
import com.github.tkurz.media.ontology.type.Coordinate;
import com.github.tkurz.media.ontology.type.SpatialEntity;
import com.github.tkurz.media.ontology.type.SpatialTemporalEntity;
import com.github.tkurz.media.ontology.type.Time;

import java.awt.geom.Area;
import java.io.StringReader;
import java.util.*;

/**
 * This class represents a media fragment. It can include id, track, temporalFragment and spatialFragment.
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragment implements SpatialTemporalEntity {

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

    @Override
    public String toString() {
        return stringValue();
    }

    @Override
    public Coordinate getCenter() {
        return spatialFragment != null ? spatialFragment.getCenter() : null;
    }

    @Override
    public Rectangle getBoundingBox() {
        return spatialFragment != null ? spatialFragment.getBoundingBox() : null;
    }

    @Override
    public Area getArea() {
        return spatialFragment != null ? spatialFragment.getArea() : null;
    }

    @Override
    public Time getStart() {
        return temporalFragment != null ? temporalFragment.getStart() : null;
    }

    @Override
    public Time getEnd() {
        return temporalFragment != null ? temporalFragment.getEnd() : null;
    }

    public String stringValue() {
        String separator = type.getDelimiter();
        if(id!=null) return separator + "id="+id;
        List<String> list = new ArrayList<>();
        if(track!=null) list.add("track="+track);
        if(temporalFragment!=null) list.add(temporalFragment.stringValue());
        if(spatialFragment !=null) list.add(spatialFragment.stringValue());
        return join(list,separator);
    }

    public String stringValue(SpatialEntity.Format format) {
        return stringValue(); //TODO should support more types
    }

    private String join(List list, String separator) {
        StringBuilder b = new StringBuilder();
        Iterator<Set> iterator = list.iterator();
        if(iterator.hasNext()) b.append(separator);
        while(iterator.hasNext()) {
            b.append(iterator.next());
            if(iterator.hasNext()) b.append("&");
        }
        return b.toString();
    }

    public MediaFragment toPixel(double width, double height) {
        //TODO should be a deep copy maybe ?!
        spatialFragment = spatialFragment != null ? spatialFragment.toPixel(width,height) : null;
        return this;
    }

    public MediaFragment toPercent(double width, double height) {
        //TODO should be a deep copy maybe ?!
        spatialFragment = spatialFragment != null ? spatialFragment.toPercent(width,height) : null;
        return this;
    }
}
