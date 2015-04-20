package com.github.tkurz.media.ontology;

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
public class ObjectTests {

    @Test
    public void testPoint() {
        SpatialEntity o = new Point(10,10);
        Assert.assertEquals(10,o.getCenter().getX(),0);
        Assert.assertEquals(10,o.getCenter().getY(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getX(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getY(),0);
        Assert.assertEquals(10,o.getBoundingBox().getLowerRight().getX(),0);
        Assert.assertEquals(10,o.getBoundingBox().getLowerRight().getY(),0);
    }

    @Test
    public void testRectangle() {
        SpatialEntity o = new Rectangle(0,10,20,30);
        Assert.assertEquals(10,o.getCenter().getX(),0);
        Assert.assertEquals(25,o.getCenter().getY(),0);
        Assert.assertEquals(0,o.getBoundingBox().getUpperLeft().getX(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getY(),0);
        Assert.assertEquals(20,o.getBoundingBox().getLowerRight().getX(),0);
        Assert.assertEquals(40,o.getBoundingBox().getLowerRight().getY(),0);
    }

    @Test
    public void testCircle() {
        SpatialEntity o = new Circle(20,20,10);
        Assert.assertEquals(20,o.getCenter().getX(),0);
        Assert.assertEquals(20,o.getCenter().getY(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getX(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getY(),0);
        Assert.assertEquals(30,o.getBoundingBox().getLowerRight().getX(),0);
        Assert.assertEquals(30,o.getBoundingBox().getLowerRight().getY(),0);
    }

    @Test
    public void testEllipse() {
        SpatialEntity o = new Rectangle(0,10,20,30);
        Assert.assertEquals(10,o.getCenter().getX(),0);
        Assert.assertEquals(25,o.getCenter().getY(),0);
        Assert.assertEquals(0,o.getBoundingBox().getUpperLeft().getX(),0);
        Assert.assertEquals(10,o.getBoundingBox().getUpperLeft().getY(),0);
        Assert.assertEquals(20,o.getBoundingBox().getLowerRight().getX(),0);
        Assert.assertEquals(40,o.getBoundingBox().getLowerRight().getY(),0);
    }

}
