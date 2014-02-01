package com.github.tkurz.media.fragments;

/**
 * Utilization methods for media fragments
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragments {

    /**
     * tests for spatial fragments
     * @param uris a set of uris
     * @return true is all uris has spatial fragments, false otherwise
     */
    private static boolean hasSpatial(MediaFragmentURI... uris) {
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasSpatialFragment()) return false;
        }
        return true;
    }

    /**
     * tests for temporal fragments
     * @param uris a set of uris
     * @return true is all uris has temporal fragments, false otherwise
     */
    private static boolean hasTemporal(MediaFragmentURI... uris) {
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasTemporalFragment()) return false;
        }
        return true;
    }

    /**
     * test if fragments are temporal comparable
     * @param uris a set of uris
     * @return true if media fragments has temporal fragments and their temporal units are equal
     */
    public static boolean temporalComparable(MediaFragmentURI... uris) {
        TemporalFragment.Unit unit = null;
        for(MediaFragmentURI uri : uris) {
            if(!uri.getMediaFragment().hasTemporalFragment()) return false;
            if(unit == null) { unit = uri.getMediaFragment().getTemporalFragment().getUnit(); }
            else if(unit != uri.getMediaFragment().getTemporalFragment().getUnit()) return false;
        }
        return true;
    }

    /**
     * test if fragments are spatial comparable
     * @param uris a set of uris
     * @return true if media fragments has spatial fragments and their spatial types are equal
     */
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
