package com.github.tkurz.media.fragments;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragments {

    private static boolean hasSpatial(MediaFragmentURI... uris) {
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasSpatialFragment()) return false;
        }
        return true;
    }

    private static boolean hasTemporal(MediaFragmentURI... uris) {
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasTemporalFragment()) return false;
        }
        return true;
    }

    public static boolean temporalComparable(MediaFragmentURI... uris) {
        TemporalFragment.Unit unit = null;
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasTemporalFragment()) return false;
            if(unit == null) { unit = uri.getMediaFragment().getTemporalFragment().getUnit(); }
            else if(unit != uri.getMediaFragment().getTemporalFragment().getUnit()) return false;
        }
        return true;
    }

    public static boolean spatialComparable(MediaFragmentURI... uris) {
        SpatialFragment.Unit unit = null;
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasSpatialFragment()) return false;
            if(unit == null) { unit = uri.getMediaFragment().getSpatialFragment().getUnit(); }
            else if(unit != uri.getMediaFragment().getSpatialFragment().getUnit()) return false;
        }
        return true;
    }

}
