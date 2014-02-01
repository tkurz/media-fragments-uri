package com.github.tkurz.media.fragments;

/**
 * Is thrown if a uri cannot be parsed to media fragment
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragmentURISyntaxException extends Exception {

    public MediaFragmentURISyntaxException(Exception e) {
        super(e);
    }

}
