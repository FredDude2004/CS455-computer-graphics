/*
   Kaleb Duchesneau 10/01/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
*/

import renderer.scene.*;
import renderer.scene.primitives.*;

import java.awt.Color;

/**
 * A three-dimensional wireframe model of the letter P.
 *
 * 1______________ 2
 * | \
 * | 8_______ 9 \ 3
 * | | | |
 * | |______| |
 * | 11 10 / 4
 * | 6_________/
 * | | 5
 * |____|
 * 0 7
 */
public class P extends Model {
    /**
     * The letter P.
     */
    public P() {
        super("P");

        // Create the front face vertices.
        this.addVertex(new Vertex(0.00, 0.00, 0.0),
                new Vertex(0.00, 1.00, 0.0),
                new Vertex(0.75, 1.00, 0.0),
                new Vertex(1.00, 0.8, 0.0),
                new Vertex(1.00, 0.6, 0.0),
                new Vertex(0.75, 0.4, 0.0),
                new Vertex(0.25, 0.4, 0.0),
                new Vertex(0.25, 0.0, 0.0));

        this.addVertex(new Vertex(0.25, 0.8, 0.0),
                new Vertex(0.75, 0.8, 0.0),
                new Vertex(0.75, 0.6, 0.0),
                new Vertex(0.25, 0.6, 0.0));

        // Create the back face vertices.
        this.addVertex(new Vertex(0.00, 0.00, -0.25),
                new Vertex(0.00, 1.00, -0.25),
                new Vertex(0.75, 1.00, -0.25),
                new Vertex(1.00, 0.8, -0.25),
                new Vertex(1.00, 0.6, -0.25),
                new Vertex(0.75, 0.4, -0.25),
                new Vertex(0.25, 0.4, -0.25),
                new Vertex(0.25, 0.0, -0.25));

        this.addVertex(new Vertex(0.25, 0.8, -0.25),
                new Vertex(0.75, 0.8, -0.25),
                new Vertex(0.75, 0.6, -0.25),
                new Vertex(0.25, 0.6, -0.25));

        // Create the Color objects.
        this.addColor(new Color(255, 0, 0), // red (0)
                new Color(0, 255, 0), // green (1)
                new Color(255, 0, 255)); // magenta (2)

        // Create the front face line segments
        // (you need to add the Color indices!).
        this.addPrimitive(new LineSegment(0, 1, 1),
                new LineSegment(1, 2, 2),
                new LineSegment(2, 3, 2),
                new LineSegment(3, 4, 0),
                new LineSegment(4, 5, 2),
                new LineSegment(5, 6, 2),
                new LineSegment(6, 7, 2),
                new LineSegment(7, 0, 2));

        this.addPrimitive(new LineSegment(8, 9, 2),
                new LineSegment(9, 10, 2),
                new LineSegment(10, 11, 2),
                new LineSegment(11, 8, 2));

        // Create the back face line segments.
        this.addPrimitive(new LineSegment(12, 13, 1),
                new LineSegment(13, 14, 2),
                new LineSegment(14, 15, 2),
                new LineSegment(15, 16, 2),
                new LineSegment(16, 17, 2),
                new LineSegment(17, 18, 2),
                new LineSegment(18, 19, 2),
                new LineSegment(19, 12, 2));

        this.addPrimitive(new LineSegment(20, 21, 2),
                new LineSegment(21, 22, 2),
                new LineSegment(22, 23, 2),
                new LineSegment(23, 20, 2));

        // Create the front face to back face line segments.
        this.addPrimitive(new LineSegment(0, 12, 1),
                new LineSegment(1, 13, 1),
                new LineSegment(2, 14, 2),
                new LineSegment(3, 15, 0),
                new LineSegment(4, 16, 0),
                new LineSegment(5, 17, 2),
                new LineSegment(6, 18, 2),
                new LineSegment(7, 19, 2),
                new LineSegment(8, 20, 2),
                new LineSegment(9, 21, 2),
                new LineSegment(10, 22, 2),
                new LineSegment(11, 23, 2));
    }
}
