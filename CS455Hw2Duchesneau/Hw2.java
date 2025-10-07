/*
   Kaleb Duchesneau 10/01/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
*/

import renderer.scene.*;
import renderer.scene.util.DrawSceneGraph;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;

/**
 * Create the frames for an animation
 * of the letters P, N, and W.
 */
public class Hw2 {
    public static void main(String[] args) {
        // Create a FrameBuffer to render our scene into.
        final int width = 900;
        final int height = 900;
        final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

        final Scene scene = new Scene();

        // Create the Models and give them an initial location.
        final P p = new P();
        final N n = new N();
        final W w = new W();

        scene.addPosition(new Position(p, "p1", new Vector(0, 0, -1.5)));
        scene.addPosition(new Position(p, "p2", new Vector(1, 0, -1.5)));
        scene.addPosition(new Position(p, "p3", new Vector(2, 0, -1.5)));

        // If you need to, print the Scene data structure to the console.
        // This can help you check that your models and scene make sense.
        // System.out.println( scene );

        // Use GraphViz to draw a picture of the Scene data structure.
        DrawSceneGraph.draw(scene, "Hw2_SG");

        Rasterize.doClipping = true;
        Rasterize.doGamma = false;
        // scene.debug = true; // Uncomment this line for debugging output.
        // Rasterize.debug = true; // Uncomment this line for more debugging output.

        // Create 300 frames of the animation.
        for (int i = 0; i < 300; ++i) {
            // Render again.
            fb.clearFB();
            Pipeline.render(scene, fb);
            fb.dumpFB2File(String.format("Hw2_frame%03d.ppm", i));

            // Update each Model's location.

        }
    }
}
