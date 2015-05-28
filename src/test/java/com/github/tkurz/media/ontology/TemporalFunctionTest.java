package com.github.tkurz.media.ontology;

import com.github.tkurz.media.ontology.exception.NotAggregatableException;
import com.github.tkurz.media.ontology.exception.NotComparableException;
import com.github.tkurz.media.ontology.function.TemporalFunction;
import com.github.tkurz.media.ontology.impl.Instant;
import com.github.tkurz.media.ontology.impl.Interval;
import junit.framework.Assert;
import org.junit.Test;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class TemporalFunctionTest {

    @Test
    public void testBefore() throws NotComparableException {

        Instant is1 = new Instant(10);
        Instant is2 = new Instant(30);
        Interval iv1 = new Interval(0D,20D);
        Interval iv2 = new Interval(40D,50D);

        Assert.assertTrue(TemporalFunction.precedes(is1, is2));
        Assert.assertTrue(TemporalFunction.precedes(is1, iv2));
        Assert.assertTrue(TemporalFunction.precedes(iv1, iv2));
        Assert.assertTrue(TemporalFunction.precedes(is2, iv2));

        Assert.assertFalse(TemporalFunction.precedes(is2, is1));
        Assert.assertFalse(TemporalFunction.precedes(is2, iv1));
        Assert.assertFalse(TemporalFunction.precedes(iv2, iv1));
        Assert.assertFalse(TemporalFunction.precedes(iv2, is1));
    }

    @Test
    public void testFinishes() throws NotComparableException {

        Instant is1 = new Instant(10);
        Interval iv1 = new Interval(0D,10D);
        Interval iv2 = new Interval(2D,10D);
        Interval iv3 = new Interval(2D,11D);

        Assert.assertTrue(TemporalFunction.finishes(is1,iv1));
        Assert.assertTrue(TemporalFunction.finishes(is1,iv2));
        Assert.assertTrue(TemporalFunction.finishes(iv2, iv1));

        Assert.assertFalse(TemporalFunction.finishes(iv1,iv2));
    }

    @Test
    public void testEquals() throws NotComparableException {

        Interval iv1 = new Interval(0D,10D);
        Interval iv2 = new Interval(2D,10D);

        Assert.assertTrue(TemporalFunction.equals(iv1, iv1));

        Assert.assertFalse(TemporalFunction.equals(iv1, iv2));
    }

    @Test
    public void testStarts() throws NotComparableException {

        Interval iv1 = new Interval(0D,10D);
        Interval iv2 = new Interval(0D,11D);

        Assert.assertTrue(TemporalFunction.starts(iv1, iv2));

        Assert.assertFalse(TemporalFunction.starts(iv2, iv1));
    }

    @Test
    public void testBoundingBox() {
        Interval iv1 = new Interval(0D,10D);
        Interval iv2 = new Interval(3D,11D);
        Interval iv3 = new Interval(20D,21D);

        Interval a1 = TemporalFunction.getBoundingBox(iv1, iv2);
        Interval a2 = TemporalFunction.getBoundingBox(iv1, iv2, iv3);
        Interval a3 = TemporalFunction.getBoundingBox(iv2, iv1);

        Assert.assertEquals(0D, a1.getStart().getValue());
        Assert.assertEquals(11D, a1.getEnd().getValue());

        Assert.assertEquals(0D, a2.getStart().getValue());
        Assert.assertEquals(21D, a2.getEnd().getValue());

        Assert.assertEquals(0D, a3.getStart().getValue());
        Assert.assertEquals(11D, a3.getEnd().getValue());
    }

    @Test
    public void testIntersect() throws NotAggregatableException {
        Interval iv1 = new Interval(0D,10D);
        Interval iv2 = new Interval(3D,11D);
        Interval iv3 = new Interval(20D,21D);

        Interval a1 = TemporalFunction.getIntersect(iv1, iv2);
        Interval a2 = TemporalFunction.getIntersect(iv2, iv1);

        Assert.assertEquals(3D, a1.getStart().getValue());
        Assert.assertEquals(10D, a1.getEnd().getValue());

        Assert.assertEquals(3D, a2.getStart().getValue());
        Assert.assertEquals(10D, a2.getEnd().getValue());

        try {
            TemporalFunction.getIntersect(iv1, iv3);
            Assert.fail();
        } catch(NotAggregatableException e) {}
    }

}
