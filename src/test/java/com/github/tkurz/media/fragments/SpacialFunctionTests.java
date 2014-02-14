package com.github.tkurz.media.fragments;

import junit.framework.Assert;
import org.junit.Test;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpacialFunctionTests {

    SpatialFragment r1;
    SpatialFragment r2;
    SpatialFragment r3;
    SpatialFragment r4;
    SpatialFragment r5;

    public SpacialFunctionTests() throws MediaFragmentURISyntaxException {
        r1 = (new MediaFragmentURI("http://example.org/video#xywh=0,0,20,20")).getMediaFragment().getSpatialFragment();
        r2 = (new MediaFragmentURI("http://example.org/video#xywh=10,10,20,20")).getMediaFragment().getSpatialFragment();
        r3 = (new MediaFragmentURI("http://example.org/video#xywh=100,100,20,20")).getMediaFragment().getSpatialFragment();
        r4 = (new MediaFragmentURI("http://example.org/video#xywh=percent:0,0,20,20")).getMediaFragment().getSpatialFragment();
        r5 = (new MediaFragmentURI("http://example.org/video#xywh=5,5,5,5")).getMediaFragment().getSpatialFragment();
    }

    @Test
    public void testOverlap() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertTrue("overlap function fails",r1.intersects(r2));
        Assert.assertTrue("overlap function fails",r2.intersects(r1));
        Assert.assertFalse("overlap function fails",r1.intersects(r3));
        Assert.assertTrue("disjoint function fails", r1.disjoint(r3));
    }

    @Test
    public void testCovers() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertFalse("overlap function fails", r1.covers(r2));
        Assert.assertTrue("overlap function fails",r1.covers(r5));
    }

    @Test
    public void testLeftBeside() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertFalse("left-beside function fails", r1.leftBeside(r2));
        Assert.assertTrue("left-beside function fails", r2.leftBeside(r3));
    }

    @Test
    public void testRightBeside() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertFalse("left-beside function fails", r1.rightBeside(r2));
        Assert.assertTrue("left-beside function fails",r3.rightBeside(r1));
    }

    @Test
    public void testIsAbove() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertTrue("isAbove function fails", r1.isAbove(r3));
        Assert.assertFalse("isAbove function fails", r1.isAbove(r2));
    }

    @Test
    public void testIsBelow() throws MediaFragmentURISyntaxException, FunctionException {
        Assert.assertFalse("isAbove function fails", r1.isBelow(r3));
        Assert.assertFalse("isAbove function fails", r1.isBelow(r2));
    }

    @Test
    public void testAreas() throws FunctionException {
        Assert.assertTrue(r4.top());
        Assert.assertFalse(r4.bottom());
        Assert.assertTrue(r4.left());
        Assert.assertFalse(r4.right());
    }

    @Test(expected = FunctionException.class)
    public void testAreas2() throws FunctionException {
        Assert.assertTrue(r1.top());
    }

    @Test(expected = FunctionException.class)
    public void compareIncomparable() throws FunctionException {
        r1.isAbove(r4);
    }

    @Test
    public void testGetIntersection() throws FunctionException {
        SpatialFragment f = r1.getIntersection(r2);
        Assert.assertEquals(10.0,f.getX());
        Assert.assertEquals(10.0,f.getY());
        Assert.assertEquals(10.0,f.getW());
        Assert.assertEquals(10.0,f.getH());
    }

    @Test(expected = FunctionException.class)
    public void testGetIntersectionFail() throws FunctionException {
        SpatialFragment f = r1.getIntersection(r4);
    }

    @Test
    public void testGetBoundingBox() throws FunctionException {
        SpatialFragment f = r1.getBoundingBox(r3);
        Assert.assertEquals(0.0,f.getX());
        Assert.assertEquals(0.0,f.getY());
        Assert.assertEquals(120.0,f.getW());
        Assert.assertEquals(120.0,f.getH());
    }

}
