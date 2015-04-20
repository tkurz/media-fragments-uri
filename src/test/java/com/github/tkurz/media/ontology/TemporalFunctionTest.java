package com.github.tkurz.media.ontology;

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

        Assert.assertTrue(TemporalFunction.before(is1,is2));
        Assert.assertTrue(TemporalFunction.before(is1,iv2));
        Assert.assertTrue(TemporalFunction.before(iv1,iv2));
        Assert.assertTrue(TemporalFunction.before(is2,iv2));

        Assert.assertFalse(TemporalFunction.before(is2,is1));
        Assert.assertFalse(TemporalFunction.before(is2,iv1));
        Assert.assertFalse(TemporalFunction.before(iv2,iv1));
        Assert.assertFalse(TemporalFunction.before(iv2,is1));
    }



}
