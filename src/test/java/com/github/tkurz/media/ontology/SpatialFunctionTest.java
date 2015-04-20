package com.github.tkurz.media.ontology;

import com.github.tkurz.media.ontology.function.SpatialFunction;
import com.github.tkurz.media.ontology.impl.Circle;
import com.github.tkurz.media.ontology.impl.Point;
import com.github.tkurz.media.ontology.impl.Rectangle;
import com.github.tkurz.media.ontology.type.SpatialEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFunctionTest {

    SpatialEntity r1 = new Rectangle(0,0,20,20);
    SpatialEntity r2 = new Rectangle(1,1,20,20);
    SpatialEntity r3 = new Rectangle(21,21,20,20);

    SpatialEntity r10 = new Rectangle(0,0,10,10);
    SpatialEntity r11 = new Rectangle(0,11,10,10);
    SpatialEntity r12 = new Rectangle(11,0,10,10);
    SpatialEntity r13 = new Rectangle(11,11,10,10);
    SpatialEntity r14 = new Rectangle(5,5,10,10);

    @Test
    public void testLeftBeside() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.leftBeside(r1, r2));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBeside(r1, r2, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBeside(r1, r3));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBeside(r1, r3, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

    }

    @Test
    public void testRightBeside() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.rightBeside(r2, r1));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBeside(r2, r1, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBeside(r3, r1));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBeside(r3, r1, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

    }

    @Test
    public void testAbove() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.above(r1, r2));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.above(r1, r2, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.above(r1, r3));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.above(r1, r3, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

    }

    @Test
    public void testBelow() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.below(r2, r1));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.below(r2, r1, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.below(r3, r1));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.below(r3, r1, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

    }

    @Test
    public void testLeftAbove() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.leftAbove(r10, r14));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftAbove(r10, r14, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftAbove(r10, r13));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftAbove(r10, r13, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));
    }

    @Test
    public void testLeftBelow() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.leftBelow(r11, r14));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBelow(r11, r14, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBelow(r11, r12));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.leftBelow(r11, r12, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));
    }

    @Test
    public void testRightAbove() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.rightAbove(r12, r14));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightAbove(r12, r14, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightAbove(r12, r11));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightAbove(r12, r11, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));
    }

    @Test
    public void testRightBelow() {

        Assert.assertFalse(SpatialFunction.DirectionalFunction.rightBelow(r13, r14));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBelow(r13, r14, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));

        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBelow(r13, r10));
        Assert.assertTrue(SpatialFunction.DirectionalFunction.rightBelow(r13, r10, SpatialFunction.DirectionalFunction.DirectionalRelationModel.Center));
    }

    SpatialEntity s1 = new Rectangle(5,5,10,10);
    SpatialEntity s2 = new Rectangle(5,5,10,10);
    SpatialEntity s3 = new Circle(5,5,10);
    SpatialEntity s4 = new Rectangle(7,7,20,20);
    SpatialEntity s5 = new Rectangle(15,5,20,20);
    SpatialEntity s6 = new Rectangle(6,6,3,3);
    SpatialEntity p = new Point(14,15);

    @Test
    public void testEquals() {
        Assert.assertTrue(SpatialFunction.TopologicalFunction.equals(s1,s2));
        Assert.assertFalse(SpatialFunction.TopologicalFunction.equals(s1, s3));
    }

    @Test
    public void testIntersects() {
        Assert.assertTrue(SpatialFunction.TopologicalFunction.intersects(s3,s4));
        Assert.assertFalse(SpatialFunction.TopologicalFunction.intersects(s3, s5));
    }

    @Test
    public void testContains() {
        Assert.assertFalse(SpatialFunction.TopologicalFunction.contains(s3, p));
        Assert.assertTrue(SpatialFunction.TopologicalFunction.contains(s2, s2));
        Assert.assertTrue(SpatialFunction.TopologicalFunction.contains(s2, s6));

    }

}
