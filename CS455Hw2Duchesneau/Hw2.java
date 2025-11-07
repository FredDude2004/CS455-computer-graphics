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

        scene.addPosition(new Position(p, "p1", new Vector(-1.5, -0.5, -1.5)));
        scene.addPosition(new Position(n, "p2", new Vector(-0.5, -0.5, -1.5)));
        scene.addPosition(new Position(w, "p3", new Vector(0.5, -0.5, -1.5)));

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

            Vector vP = scene.getPosition(0).getTranslation();
            Vector vN = scene.getPosition(1).getTranslation();
            Vector vW = scene.getPosition(2).getTranslation();
            double pX = vP.x, pY = vP.y, pZ = vP.z;
            double nX = vN.x, nY = vN.y, nZ = vN.z;
            double wX = vW.x, wY = vW.y, wZ = vW.z;

            // Update each Model's location.
            if (i < 25) {
                pZ -=  0.04;
            } else if (i < 75) {
                nX -= 0.02;
                wX -= 0.02;
                pX += 0.04;
            } else if (i < 100) {
                pZ +=  0.04;
            } else if (i < 125) {
                nY += 0.04;
            } else if (i < 175) {
                nX += 0.04;
                wX -= 0.02;
                pX -= 0.02;
            } else if (i < 200) {
                nY -= 0.04;
            } else if (i < 225) {
                pY -= 0.04;
                nY -= 0.04;
            } else if (i < 250) {
                wX += 0.04;
                pX -= 0.04;
            } else if (i < 275) {
                wX += 0.04;
                nX -= 0.04;
            } else {
                pY += 0.04;
                nY += 0.04;
            }

            double pXDiff = pX - vP.x, pYDiff = pY - vP.y, pZDiff = pZ - vP.z;
            double nXDiff = nX - vN.x, nYDiff = nY - vN.y, nZDiff = nZ - vN.z;
            double wXDiff = wX - vW.x, wYDiff = wY - vW.y, wZDiff = wZ - vW.z;

            scene.setPosition(0, new Position(p, "p1", vP.plus(new Vector(pXDiff, pYDiff, pZDiff))));
            scene.setPosition(1, new Position(n, "p2", vN.plus(new Vector(nXDiff, nYDiff, nZDiff))));
            scene.setPosition(2, new Position(w, "p3", vW.plus(new Vector(wXDiff, wYDiff, wZDiff))));
        }
    }
}
