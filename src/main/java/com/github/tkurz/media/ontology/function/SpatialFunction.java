package com.github.tkurz.media.ontology.function;

import com.github.tkurz.media.ontology.impl.Rectangle;
import com.github.tkurz.media.ontology.type.SpatialEntity;

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * ...
 * <p/>
 * Author: Thomas Kurz (tkurz@apache.org)
 */
public class SpatialFunction {

    public static class DirectionalFunction {
        public static DirectionalRelationModel DEFAULT_DIRECTIONAL_MODEL = DirectionalRelationModel.BoundingBox;

        public static enum DirectionalRelationModel {
            Center, BoundingBox
        }

        public static boolean leftBeside(SpatialEntity e1, SpatialEntity e2) {
            return leftBeside(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean leftBeside(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            switch (model) {
                case Center:
                    return e1.getCenter().getX() < e2.getCenter().getX();
                case BoundingBox:
                    return e1.getBoundingBox().getLowerRight().getX() < e2.getBoundingBox().getUpperLeft().getX();
            }
            return false;
        }

        public static boolean rightBeside(SpatialEntity e1, SpatialEntity e2) {
            return rightBeside(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean rightBeside(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            switch (model) {
                case Center:
                    return e1.getCenter().getX() > e2.getCenter().getX();
                case BoundingBox:
                    return e1.getBoundingBox().getUpperLeft().getX() > e2.getBoundingBox().getLowerRight().getX();
            }
            return false;
        }

        public static boolean below(SpatialEntity e1, SpatialEntity e2) {
            return below(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean below(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            switch (model) {
                case Center:
                    return e1.getCenter().getY() > e2.getCenter().getY();
                case BoundingBox:
                    return e1.getBoundingBox().getUpperLeft().getY() > e2.getBoundingBox().getLowerRight().getY();
            }
            return false;
        }

        public static boolean above(SpatialEntity e1, SpatialEntity e2) {
            return above(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean above(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            switch (model) {
                case Center:
                    return e1.getCenter().getY() < e2.getCenter().getY();
                case BoundingBox:
                    return e1.getBoundingBox().getLowerRight().getY() < e2.getBoundingBox().getUpperLeft().getY();
            }
            return false;
        }

        public static boolean leftAbove(SpatialEntity e1, SpatialEntity e2) {
            return leftAbove(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean leftAbove(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            return leftBeside(e1, e2, model) && above(e1,e2,model);
        }

        public static boolean rightAbove(SpatialEntity e1, SpatialEntity e2) {
            return rightAbove(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean rightAbove(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            return rightBeside(e1,e2,model) && above(e1,e2,model);
        }

        public static boolean leftBelow(SpatialEntity e1, SpatialEntity e2) {
            return leftBelow(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean leftBelow(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            return leftBeside(e1, e2,model) && below(e1,e2,model);
        }

        public static boolean rightBelow(SpatialEntity e1, SpatialEntity e2) {
            return rightBelow(e1, e2, DEFAULT_DIRECTIONAL_MODEL);
        }

        public static boolean rightBelow(SpatialEntity e1, SpatialEntity e2, DirectionalRelationModel model) {
            return rightBeside(e1,e2,model) && below(e1,e2,model);
        }
    }

    public static class TopologicalFunction {

        public static boolean equals(SpatialEntity e1, SpatialEntity e2) {
            return e1.getArea().equals(e2.getArea());
        }

        public static boolean disjoint(SpatialEntity e1, SpatialEntity e2) {
            Area a1 = e1.getArea();
            Area a2 = e2.getArea();
            a1.intersect(a2);
            return a1.isEmpty();
        }

        public static boolean touches(SpatialEntity e1, SpatialEntity e2) {
            if(e1 instanceof Rectangle && e2 instanceof Rectangle) {
                return (e1.getBoundingBox().getX()+ e1.getBoundingBox().getWidth() == e2.getBoundingBox().getX()
                        || e1.getBoundingBox().getY() + e1.getBoundingBox().getHeight() == e2.getBoundingBox().getY()
                        || e1.getBoundingBox().getX() == e2.getBoundingBox().getX() + e2.getBoundingBox().getWidth()
                        || e1.getBoundingBox().getY() == e2.getBoundingBox().getY() + e2.getBoundingBox().getHeight()
                );
            }
            throw new RuntimeException("Not yet implemented for non-rectangular entities");
        }

        public static boolean contains(SpatialEntity e1, SpatialEntity e2) {

            if(e1 instanceof Point2D) return false;

            Area a1 = e1.getArea();

            if(e2 instanceof Point2D) return a1.contains((Point2D) e2);

            Area a2 = e2.getArea();
            a1.intersect(a2);
            return a1.equals(a2);
        }

        public static boolean covers(SpatialEntity e1, SpatialEntity e2) {
            if(e1 instanceof Rectangle && e2 instanceof Rectangle) {
                return (e1.getBoundingBox().getX() <= e2.getBoundingBox().getX()
                        && e1.getBoundingBox().getY() <= e2.getBoundingBox().getY()
                        && e1.getBoundingBox().getX() + e1.getBoundingBox().getWidth() >= e2.getBoundingBox().getX() + e2.getBoundingBox().getWidth()
                        && e1.getBoundingBox().getY() + e1.getBoundingBox().getHeight() >= e2.getBoundingBox().getY() + e2.getBoundingBox().getHeight()
                );
            }
            throw new RuntimeException("Not yet implemented for non-rectangular entities");
        }

        public static boolean intersects(SpatialEntity e1, SpatialEntity e2) {
            return !disjoint(e1,e2);
        }

        public static boolean within(SpatialEntity e1, SpatialEntity e2) {
            return contains(e2,e1);
        }

        public static boolean coveredBy(SpatialEntity e1, SpatialEntity e2) {
            return covers(e2,e1);
        }

        public static boolean crosses(SpatialEntity e1, SpatialEntity e2) {
            if(e1 instanceof Rectangle && e2 instanceof Rectangle) {
                return e1.getBoundingBox().intersects(e2.getBoundingBox());
            }
            throw new RuntimeException("Not yet implemented for non-rectangular entities");
        }

        public static boolean overlaps(SpatialEntity e1, SpatialEntity e2) {
            return intersects(e1,e2) && !equals(e1,e2);
        }

    }

    public static Rectangle getBoundingBox(SpatialEntity... entities) {
        Rectangle2D combination = entities[0].getBoundingBox();
        for(int i = 1; i < entities.length; i++) {
            combination = combination.createUnion(entities[i].getBoundingBox());
        }
        return new Rectangle(combination);
    }

    public static Rectangle getIntersect(SpatialEntity... entities) {
        Rectangle2D combination = entities[0].getBoundingBox();
        for(int i = 1; i < entities.length; i++) {
            combination = combination.createIntersection(entities[i].getBoundingBox());
        }
        return new Rectangle(combination);
    }

}
