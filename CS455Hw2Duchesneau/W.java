/*
   Kaleb Duchesneau 10/01/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
*/

import renderer.scene.*;
import renderer.scene.primitives.*;

import java.awt.Color;

/**
 * A three-dimensional wireframe model of the letter W.
 * I drew some a picture with MSPaint
 *
 */
public class W extends Model {
    /**
     * The letter W.
     */
    public W() {
        super("W");

        // Create the front face vertices.
        this.addVertex(new Vertex(0.2, 0.0, 0.0),
                new Vertex(0.0, 1.0, 0.0),
                new Vertex(0.2, 1.0, 0.0),
                new Vertex(0.3, 0.5, 0.0),
                new Vertex(0.4, 1.0, 0.0),
                new Vertex(0.6, 1.0, 0.0),
                new Vertex(0.7, 0.5, 0.0),
                new Vertex(0.8, 1.0, 0.0),
                new Vertex(1.0, 1.0, 0.0),
                new Vertex(0.8, 0.0, 0.0),
                new Vertex(0.6, 0.0, 0.0),
                new Vertex(0.5, 0.5, 0.0),
                new Vertex(0.4, 0.0, 0.0));

        // Create the back face vertices.
        this.addVertex(new Vertex(0.2, 0.0, -0.25),
                new Vertex(0.0, 1.0, -0.25),
                new Vertex(0.2, 1.0, -0.25),
                new Vertex(0.3, 0.5, -0.25),
                new Vertex(0.4, 1.0, -0.25),
                new Vertex(0.6, 1.0, -0.25),
                new Vertex(0.7, 0.5, -0.25),
                new Vertex(0.8, 1.0, -0.25),
                new Vertex(1.0, 1.0, -0.25),
                new Vertex(0.8, 0.0, -0.25),
                new Vertex(0.6, 0.0, -0.25),
                new Vertex(0.5, 0.5, -0.25),
                new Vertex(0.4, 0.0, -0.25));

        // Create the Color objects.
        this.addColor(new Color(255, 0, 0), // red (0)
                new Color(0, 255, 0), // green (1)
                new Color(255, 0, 255)); // magenta (2)

        // Create the front face line segments.
        this.addPrimitive(new LineSegment(0, 1, 2),
                new LineSegment(1, 2, 1),
                new LineSegment(2, 3, 2),
                new LineSegment(3, 4, 2),
                new LineSegment(4, 5, 1),
                new LineSegment(5, 6, 2),
                new LineSegment(6, 7, 2),
                new LineSegment(7, 8, 1),
                new LineSegment(8, 9, 2),
                new LineSegment(9, 10, 0),
                new LineSegment(10, 11, 2),
                new LineSegment(11, 12, 2),
                new LineSegment(12, 0, 0));

        // Create the back face line segments.
        this.addPrimitive(new LineSegment(13, 14, 2),
                new LineSegment(14, 15, 1),
                new LineSegment(15, 16, 2),
                new LineSegment(16, 17, 2),
                new LineSegment(17, 18, 1),
                new LineSegment(18, 19, 2),
                new LineSegment(19, 20, 2),
                new LineSegment(20, 21, 1),
                new LineSegment(21, 22, 2),
                new LineSegment(22, 23, 0),
                new LineSegment(23, 24, 2),
                new LineSegment(24, 25, 2),
                new LineSegment(25, 13, 0));

        // Create the front face to back face line segments.
        this.addPrimitive(new LineSegment(0, 13, 0),
                new LineSegment(1, 14, 1),
                new LineSegment(2, 15, 1),
                new LineSegment(3, 16, 2),
                new LineSegment(4, 17, 1),
                new LineSegment(5, 18, 1),
                new LineSegment(6, 19, 2),
                new LineSegment(7, 20, 1),
                new LineSegment(8, 21, 1),
                new LineSegment(9, 22, 0),
                new LineSegment(10, 23, 0),
                new LineSegment(11, 24, 2),
                new LineSegment(12, 25, 2));
    }
}
