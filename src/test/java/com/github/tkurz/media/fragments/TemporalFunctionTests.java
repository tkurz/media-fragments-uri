package com.github.tkurz.media.fragments;

import com.github.tkurz.media.fragments.base.MediaFragmentURI;
import com.github.tkurz.media.fragments.exceptions.MediaFragmentURISyntaxException;
import com.github.tkurz.media.fragments.temporal.NPTFragment;
import com.github.tkurz.media.fragments.temporal.TemporalFragment;
import junit.framework.Assert;
import org.junit.Test;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class TemporalFunctionTests {

    TemporalFragment f1;
    TemporalFragment f2;
    TemporalFragment f3;
    TemporalFragment f4;
    TemporalFragment f5;
    TemporalFragment f6;

    public TemporalFunctionTests() throws MediaFragmentURISyntaxException {
        f1 = (new MediaFragmentURI("http://sample.org/video.mp4#t=0,10")).getMediaFragment().getTemporalFragment();
        f2 = (new MediaFragmentURI("http://sample.org/video.mp4#t=5,15")).getMediaFragment().getTemporalFragment();
        f3 = (new MediaFragmentURI("http://sample.org/video.mp4#t=10,20")).getMediaFragment().getTemporalFragment();
        f4 = (new MediaFragmentURI("http://sample.org/video.mp4#t=2,3")).getMediaFragment().getTemporalFragment();
        f5 = (new MediaFragmentURI("http://sample.org/video.mp4#t=100,101")).getMediaFragment().getTemporalFragment();
        f6 = (new MediaFragmentURI("http://sample.org/video.mp4#t=0,10")).getMediaFragment().getTemporalFragment();
    }

    @Test
    public void temporalAfterTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.after(f2));
        Assert.assertFalse(f2.after(f1));
        Assert.assertTrue(f3.after(f1));
    }

    @Test
    public void temporalBeforeTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.before(f2));
        Assert.assertFalse(f2.before(f1));
        Assert.assertTrue(f1.before(f3));
    }

    @Test
    public void temporalEqualsTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.equal(f2));
        Assert.assertTrue(f1.equal(f1));
        Assert.assertTrue(f1.equal(f6));
    }


    @Test
    public void temporalMeetsTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.meets(f2));
        Assert.assertTrue(f1.meets(f3));
        Assert.assertTrue(f3.meets(f1));
    }

    @Test
    public void temporalOverlapsTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.overlaps(f3));
        Assert.assertTrue(f1.overlaps(f2));
        Assert.assertTrue(f2.overlaps(f1));
    }

    @Test
    public void temporalIncludesTest() throws MediaFragmentURISyntaxException {
        Assert.assertFalse(f1.contains(f3));
        Assert.assertTrue(f1.contains(f4));
        Assert.assertFalse(f4.contains(f1));
    }

    @Test
    public void temporalIntersectionTest() throws MediaFragmentURISyntaxException {
        TemporalFragment tf1 = f2.getIntersection(f1);

        Assert.assertTrue(tf1 instanceof NPTFragment);

        NPTFragment ntf = (NPTFragment) tf1;

        Assert.assertEquals(5.0, ntf.getStart().getValue());
        Assert.assertEquals(10.0, ntf.getEnd().getValue());

        TemporalFragment tf2 = f1.getIntersection(f3);

        Assert.assertNull(tf2);
    }

    @Test
    public void temporalIntermediateTest() throws MediaFragmentURISyntaxException {
        TemporalFragment tf1 = f1.getIntermediate(f5);

        Assert.assertTrue(tf1 instanceof NPTFragment);

        NPTFragment ntf = (NPTFragment) tf1;

        Assert.assertEquals(10.0, ntf.getStart().getValue());
        Assert.assertEquals(100.0, ntf.getEnd().getValue());

        TemporalFragment tf2 = f1.getIntermediate(f2);

        Assert.assertNull(tf2);
    }

    @Test
    public void temporalUnionTest() throws MediaFragmentURISyntaxException {
        TemporalFragment tf1 = f1.getUnion(f2);

        Assert.assertTrue(tf1 instanceof NPTFragment);

        NPTFragment ntf = (NPTFragment) tf1;

        Assert.assertEquals(0.0, ntf.getStart().getValue());
        Assert.assertEquals(15.0, ntf.getEnd().getValue());

        TemporalFragment tf2 = f1.getUnion(f5);

        Assert.assertNull(tf2);
    }

    @Test
    public void temporalBoundingBoxTest() throws MediaFragmentURISyntaxException {
        TemporalFragment tf1 = f3.getBoundingBox(f5);

        Assert.assertTrue(tf1 instanceof NPTFragment);

        NPTFragment ntf = (NPTFragment) tf1;

        Assert.assertEquals(10.0, ntf.getStart().getValue());
        Assert.assertEquals(101.0, ntf.getEnd().getValue());

        TemporalFragment tf2 = f5.getBoundingBox(f3);

        Assert.assertTrue(tf2 instanceof NPTFragment);

        NPTFragment ntf2 = (NPTFragment) tf2;

        Assert.assertEquals(10.0, ntf2.getStart().getValue());
        Assert.assertEquals(101.0, ntf2.getEnd().getValue());

    }

}
