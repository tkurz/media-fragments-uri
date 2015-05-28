package com.github.tkurz.media.ontology.type;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public interface TemporalEntity {

    public Time getStart();
    public Time getEnd();

    public String stringValue();
}
