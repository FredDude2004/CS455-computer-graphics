import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;

public class Problem8 {
    public static void main(String[] args) {
        final int height = 100;
        final int width = 100;
        FrameBuffer fb = new FrameBuffer(width, height, Color.white);
        final Scene scene = new Scene();

        Model m = new Model("Problem 8");
        scene.addPosition(new Position(m, "p0", new Vector(0, 0, 0)));

        m.addVertex(new Vertex(0, 1, 0),
                new Vertex(1, 0, 0),
                new Vertex(-1, -1, 0));
        m.addColor(Color.black);
        m.addPrimitive(new LineSegment(0, 1, 0), // vertex, vertex, color
                new LineSegment(1, 2, 0),
                new LineSegment(2, 0, 0));
        Position p = new Position(m, "p0", new Vector(0, 0, 0));
        scene.setPosition(new Position(m, "p0", new Vector(0, 0, 0)));

        System.out.println(scene);

        fb.clearFB();
        Pipeline.render(scene, fb);
        fb.dumpFB2File("Problem8.ppm");


        System.out.println("I'm Here!");
    }
}
