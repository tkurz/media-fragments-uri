package com.github.tkurz.media.ontology.utils;

/**
 * @author Thomas Kurz (tkurz@apache.org)
 * @since 14.11.16.
 */
public class Utils {

    public static String prettyPrint(double d) {
        return String.valueOf(d).replaceAll("\\.0$","");
    }
}
