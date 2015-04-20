package com.github.tkurz.media.fragments;

import com.github.tkurz.media.fragments.base.MediaFragment;
import com.github.tkurz.media.fragments.base.MediaFragmentURI;
import com.github.tkurz.media.fragments.exceptions.MediaFragmentURISyntaxException;
import com.github.tkurz.media.fragments.spatial.SpatialFragment;
import com.github.tkurz.media.fragments.temporal.Clocktime;
import com.github.tkurz.media.fragments.temporal.NPTFragment;
import junit.framework.Assert;
import org.junit.Test;

import java.io.StringReader;

/**
 * Test media fragment uri implementation
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class MediaFragmentURITest {

    @Test
    public void parseIdFragment() throws ParseException {

        final String fragment = "id=Cap%C3%ADtulo%202";
        FragmentParser p = new FragmentParser(new StringReader(fragment));
        MediaFragment mediaFragment = p.run();

        Assert.assertTrue("id is not properly parsed",mediaFragment.hasId());
        Assert.assertEquals("id is not properly parsed","Cap%C3%ADtulo%202",mediaFragment.getId());

    }

    @Test
    public void parseTrackFragment() throws ParseException {
        final String fragment = "track=audio";
        FragmentParser p = new FragmentParser(new StringReader(fragment));
        MediaFragment mediaFragment = p.run();

        Assert.assertTrue("track is not properly parsed",mediaFragment.hasTrack());
        Assert.assertEquals("track is not properly parsed","audio",mediaFragment.getTrack());
    }

    @Test
    public void parseSpatialFragmentPixel1() throws ParseException {
        final String region = "xywh=160,120,320,240";
        FragmentParser p = new FragmentParser(new StringReader(region));
        MediaFragment mediaFragment = p.run();

        Assert.assertTrue("region is not properly parsed", mediaFragment.hasSpatialFragment());
        Assert.assertEquals("region parsed properly", region, mediaFragment.getSpatialFragment().toString());
    }

    @Test
    public void parseSpatialFragmentPixel2() throws ParseException {
        final String region = "xywh=pixel:160,120,320,240";
        FragmentParser p = new FragmentParser(new StringReader(region));
        MediaFragment mediaFragment = p.run();

        Assert.assertTrue("region is not properly parsed", mediaFragment.hasSpatialFragment());
        Assert.assertEquals("region parsed properly", "xywh=160,120,320,240", mediaFragment.getSpatialFragment().toString());
    }

    @Test
    public void parseSpatialFragmentPercent() throws ParseException {
        final String region = "xywh=percent:25,25,50,50";
        FragmentParser p = new FragmentParser(new StringReader(region));
        MediaFragment mediaFragment = p.run();

        Assert.assertTrue("region is not properly parsed", mediaFragment.hasSpatialFragment());
        Assert.assertEquals("region parsed properly", region, mediaFragment.getSpatialFragment().toString());
    }

    @Test
    public void parseNPTFragment1() throws ParseException {
        final String t1 = "t=10,20";
        final String t2 = "t=,20";
        final String t3 = "t=10";
        final String t4 = "t=npt:10,20";

        FragmentParser p1 = new FragmentParser(new StringReader(t1));
        MediaFragment m1 = p1.run();

        Assert.assertTrue("npt is not parsed properly",m1.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m1.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf1 = (NPTFragment) m1.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed", 10.0, nptf1.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",20.0,nptf1.getEnd().getValue());
        FragmentParser p2 = new FragmentParser(new StringReader(t2));
        MediaFragment m2 = p2.run();

        Assert.assertTrue("npt is not parsed properly",m2.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m2.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf2 = (NPTFragment) m2.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed",0.0,nptf2.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",20.0,nptf2.getEnd().getValue());

        FragmentParser p3 = new FragmentParser(new StringReader(t3));
        MediaFragment m3 = p3.run();

        Assert.assertTrue("npt is not parsed properly",m3.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m3.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf3 = (NPTFragment) m3.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed",10.0,nptf3.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",Double.POSITIVE_INFINITY,nptf3.getEnd().getValue());

        FragmentParser p4 = new FragmentParser(new StringReader(t4));
        MediaFragment m4 = p4.run();

        Assert.assertTrue("npt is not parsed properly",m4.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m4.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf4 = (NPTFragment) m4.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed",10.0,nptf4.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",20.0,nptf4.getEnd().getValue());

    }

    @Test
    public void parseNPTFragment2() throws ParseException {
        String t1="t=npt:,121.5";
        String t2="t=0:02:00,121.5";
        String t3="t=npt:120,0:02:01.5";

        FragmentParser p1 = new FragmentParser(new StringReader(t1));
        MediaFragment m1 = p1.run();

        Assert.assertTrue("npt is not parsed properly",m1.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m1.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf1 = (NPTFragment) m1.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed", 0.0, nptf1.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",121.5,nptf1.getEnd().getValue());

        FragmentParser p2 = new FragmentParser(new StringReader(t2));
        MediaFragment m2 = p2.run();

        Assert.assertTrue("npt is not parsed properly",m2.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m2.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf2 = (NPTFragment) m2.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed",120.0,nptf2.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",121.5,nptf2.getEnd().getValue());

        FragmentParser p3 = new FragmentParser(new StringReader(t3));
        MediaFragment m3 = p3.run();

        Assert.assertTrue("npt is not parsed properly",m3.hasTemporalFragment());
        Assert.assertTrue("npt is not parsed properly",m3.getTemporalFragment() instanceof NPTFragment);

        NPTFragment nptf3 = (NPTFragment) m3.getTemporalFragment();
        Assert.assertEquals("npt start value is not properly parsed",120.0,nptf3.getStart().getValue());
        Assert.assertEquals("npt end value is not properly parsed",121.5,nptf3.getEnd().getValue());

    }

    @Test(expected = ParseException.class)
    public void parseSMPTE() throws ParseException {
        String t = "t=smpte:10:10:10,11:11:11";

        FragmentParser p1 = new FragmentParser(new StringReader(t));
        p1.run();
    }

    @Test(expected = ParseException.class)
    public void parseUndefinedFormat() throws ParseException {
        String t = "t=abc:1,2,3";

        FragmentParser p1 = new FragmentParser(new StringReader(t));
        p1.run();
    }

    @Test
    public void testMediaURIParsing() throws MediaFragmentURISyntaxException {
        String uri_string = "http://example.org/video.mp4#t=10,20&xywh=160,120,320,240";
        MediaFragmentURI uri = new MediaFragmentURI(uri_string);

        Assert.assertTrue("region is not properly parsed",uri.getMediaFragment().hasSpatialFragment());
        Assert.assertTrue("npt is not properly parsed",uri.getMediaFragment().hasTemporalFragment());
    }

    @Test
    public void testToString() throws MediaFragmentURISyntaxException {
        String uri_string = "http://example.org/video.mp4#t=10,20&xywh=160,120,320,240";
        MediaFragmentURI uri = new MediaFragmentURI(uri_string);

        Assert.assertEquals("toString method is not correct",uri_string,uri.toString());

        String uri_string2 = "http://example.org/video.mp4#track=audio";
        MediaFragmentURI uri2 = new MediaFragmentURI(uri_string2);

        Assert.assertEquals("toString method is not correct",uri_string2,uri2.toString());

        String uri_string3 = "http://example.org/video.mp4#id=123";
        MediaFragmentURI uri3 = new MediaFragmentURI(uri_string3);

        Assert.assertEquals("toString method is not correct",uri_string3,uri3.toString());
    }

    @Test
    public void testOutput() throws MediaFragmentURISyntaxException {
        String uri_string = "http://example.org/video.mp4#t=10.1,20";
        MediaFragmentURI uri = new MediaFragmentURI(uri_string);

        Assert.assertEquals("toString method is not correct",uri_string,uri.toString());
    }

    @Test
    public void testModification() throws MediaFragmentURISyntaxException {
        String uri_string = "http://example.org/video.mp4";
        MediaFragmentURI uri = new MediaFragmentURI(uri_string);

        uri.getMediaFragment().setSpatialFragment(new SpatialFragment(10, 20, 30, 40));
        uri.getMediaFragment().setTemporalFragment(new NPTFragment(Clocktime.ZERO,new Clocktime(10)));

        Assert.assertEquals("values are not stored correctly", "http://example.org/video.mp4#xywh=10,20,30,40&t=,10", uri.toString());
    }

    @Test
    public void testURIParser() throws MediaFragmentURISyntaxException {
        String s1 = "http://example.org/video.mp4#t=10,20";
        String s2 = "http://example.org/video.mp4?t=10,20";
        MediaFragmentURI u1 = new MediaFragmentURI(s1);
        MediaFragmentURI u2 = new MediaFragmentURI(s2);

        Assert.assertEquals(10D, ((NPTFragment)u1.getMediaFragment().getTemporalFragment()).getStart().getValue());
        Assert.assertEquals(10D, ((NPTFragment)u2.getMediaFragment().getTemporalFragment()).getStart().getValue());

        Assert.assertEquals(s1, u1.toString());
        Assert.assertEquals(s2, u2.toString());

    }

    @Test
    public void compareClocktime() {
        Clocktime c1 = new Clocktime(0);
        Clocktime c2 = new Clocktime(0);
        Clocktime c3 = new Clocktime(1);

        Assert.assertEquals(-1, c1.compareTo(c3));
        Assert.assertEquals(1,c3.compareTo(c2));
        Assert.assertEquals(0,c1.compareTo(c2));
    }
}
