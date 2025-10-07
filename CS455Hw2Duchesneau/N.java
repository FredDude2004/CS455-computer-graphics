/*
   Kaleb Duchesneau 10/01/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
*/

import renderer.scene.*;
import renderer.scene.primitives.*;

import java.awt.Color;

/**
 * A three-dimensional wireframe model of the letter N.
 * I drew pictures in MSPaint
 */
public class N extends Model {
    /**
     * The letter N.
     */
    public N() {
        super("N");

        // Create the front face vertices.
        this.addVertex(new Vertex(0.0, 0.0, 0.0),
                new Vertex(0.0, 1.0, 0.0),
                new Vertex(0.25, 1.0, 0.0),
                new Vertex(0.75, 0.5, 0.0),
                new Vertex(0.75, 1.0, 0.0),
                new Vertex(1.0, 1.0, 0.0),
                new Vertex(1.0, 0.0, 0.0),
                new Vertex(0.75, 0.0, 0.0),
                new Vertex(0.25, 0.5, 0.0),
                new Vertex(0.25, 0.0, 0.0));

        // Create the back face vertices.
        this.addVertex(new Vertex(0.0, 0.0, -0.25),
                new Vertex(0.0, 1.0, -0.25),
                new Vertex(0.25, 1.0, -0.25),
                new Vertex(0.75, 0.5, -0.25),
                new Vertex(0.75, 1.0, -0.25),
                new Vertex(1.0, 1.0, -0.25),
                new Vertex(1.0, 0.0, -0.25),
                new Vertex(0.75, 0.0, -0.25),
                new Vertex(0.25, 0.5, -0.25),
                new Vertex(0.25, 0.0, -0.25));

        // Create the Color objects.
        this.addColor(new Color(255, 0, 0), // red (0)
                new Color(0, 255, 0), // green (1)
                new Color(255, 0, 255)); // magenta (2)

        // Create the front face line segments.
        this.addPrimitive(new LineSegment(0, 1, 1),
                new LineSegment(1, 2, 1),
                new LineSegment(2, 3, 2),
                new LineSegment(3, 4, 0),
                new LineSegment(4, 5, 0),
                new LineSegment(5, 6, 0),
                new LineSegment(6, 7, 0),
                new LineSegment(7, 8, 2),
                new LineSegment(8, 9, 1),
                new LineSegment(9, 0, 1));

        // Create the back face line segments.
        this.addPrimitive(new LineSegment(10, 11, 1),
                new LineSegment(11, 12, 1),
                new LineSegment(12, 13, 2),
                new LineSegment(13, 14, 0),
                new LineSegment(14, 15, 0),
                new LineSegment(15, 16, 0),
                new LineSegment(16, 17, 0),
                new LineSegment(17, 18, 2),
                new LineSegment(18, 19, 1),
                new LineSegment(19, 10, 1));

        // Create the front face to back face line segments.
        this.addPrimitive(new LineSegment(0, 10, 1),
                new LineSegment(1, 11, 1),
                new LineSegment(2, 12, 1),
                new LineSegment(3, 13, 0),
                new LineSegment(4, 14, 0),
                new LineSegment(5, 15, 0),
                new LineSegment(6, 16, 0),
                new LineSegment(7, 17, 0),
                new LineSegment(8, 18, 1),
                new LineSegment(9, 19, 1));

    }
}
