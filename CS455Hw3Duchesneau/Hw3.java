/*
   Kaleb Duchesneau 11/14/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
 */

import renderer.scene.*;
import renderer.scene.util.ModelShading;
import renderer.models_L.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Build a simple GUI that displays and manipulates models.
 */
public class Hw3 {
    // The GUI state data (the Model part of MVC).
    private final FrameBufferPanel fbp;
    private Scene scene;
    private int modelIndex = 29;  // sphere
    private int colorChoice = 2;  // blue
    private int currentAxis = 0;  // 0==x-axis, 1==y-axis, 2==z-axis
    private int xAxisSliderValue = 50;
    private int yAxisSliderValue = 50;
    private int zAxisSliderValue = 0;
    private int screenshotNumber = 0;

    private Hw3() {
        scene = new Scene("Hw3");

        scene.addPosition(new Position(new Axes2D(-1, 1, -1, 1, 5, 5, Color.red),
                                                    "p0",
                                                    new Vector(0, 0, -1)));
        scene.addPosition(new Position(ModelList.modelObjects[modelIndex],
                                        "p1",
                                        new Vector(0, 0, -2)));

        ModelShading.setColor(scene.getPosition(1).getModel(), Color.blue);  // colorChoice == 2

        Rasterize.doClipping = true;

        // Create the GUI.

        // Create a FrameBufferPanel that holds a FrameBuffer.
        final int width = 800;
        final int height = 800;
        this.fbp = new FrameBufferPanel(width, height, Color.white);

        // Create a JFrame that will hold the FrameBufferPanel.
        final JFrame jf = new JFrame("Hw 3");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(this.fbp, BorderLayout.CENTER);

        // Create two panels to hold gui components.
        final JPanel southPanel = new JPanel();
        jf.add(southPanel, BorderLayout.SOUTH);

        final Box eastPanel = new Box(BoxLayout.Y_AXIS);
        jf.add(eastPanel, BorderLayout.EAST);

        // Fill the south panel with components.
        // Create the components.
        final JComboBox<String> modelNames = new JComboBox<>(ModelList.modelNames);
        modelNames.getModel().setSelectedItem(ModelList.modelNames[modelIndex]);

        final JComboBox<String> colorNames = new JComboBox<>(new String[]{
                "Red",                 // 0
                "Green",               // 1
                "Blue",                // 2
                "Orange",              // 3
                "Cyan",                // 4
                "Magenta",             // 5
                "Black",               // 6
                "Random Color",        // 7
                "Random Edge Color",   // 8
                "Random Vertex Color"  // 9
        });
        colorNames.getModel().setSelectedItem(colorNames.getItemAt(colorChoice));

        final JRadioButton xAxisButton = new JRadioButton("x-axis");
        final JRadioButton yAxisButton = new JRadioButton("y-axis");
        final JRadioButton zAxisButton = new JRadioButton("z-axis");
        final ButtonGroup axesGroup = new ButtonGroup();
        axesGroup.add(xAxisButton);
        axesGroup.add(yAxisButton);
        axesGroup.add(zAxisButton);
        xAxisButton.setSelected(true);
        xAxisButton.setMnemonic(KeyEvent.VK_X);
        yAxisButton.setMnemonic(KeyEvent.VK_Y);
        zAxisButton.setMnemonic(KeyEvent.VK_Z);

        final JSlider axisSlider = new JSlider(JSlider.HORIZONTAL);

        // Add the components to the south panel.
        southPanel.add(modelNames);
        southPanel.add(colorNames);
        southPanel.add(xAxisButton);
        southPanel.add(yAxisButton);
        southPanel.add(zAxisButton);
        southPanel.add(axisSlider);


        // Fill the east panel with components (there are seven components).
        // Create the components
        final JCheckBox toggleAxesButton = new JCheckBox("Axes", true);
        final JCheckBox toggleClipping = new JCheckBox("Clipping", true);

        final JRadioButton perspectiveProjection = new JRadioButton("Perspective");
        final JRadioButton orthographicsProjection = new JRadioButton("Orthographic");
        final ButtonGroup perspectiveGroup = new ButtonGroup();
        perspectiveProjection.setSelected(true);
        perspectiveGroup.add(perspectiveProjection);
        perspectiveGroup.add(orthographicsProjection);

        final JCheckBox toggleDebug = new JCheckBox("Debug");
        final JButton information = new JButton("Information");
        final JButton screenshot = new JButton("Screenshot");


        // Add the components to the east panel.
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(toggleAxesButton);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(toggleClipping);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(perspectiveProjection);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(orthographicsProjection);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(toggleDebug);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(information);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(screenshot);
        eastPanel.add(Box.createVerticalGlue());


        // Make the gui visible.
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);


        // Define the event handlers (the Controller part of MVC).
        // Define the south panel event handlers (four of them).


        // This is the event handler for the combo box of model names.
        // This event handler is written as an "anonymous inner class."
        modelNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e);

                @SuppressWarnings("unchecked")
                final JComboBox<String> cb = (JComboBox<String>) e.getSource();
                final String modelNumber = ((String) cb.getSelectedItem()).substring(0, 2);

                modelIndex = -1 + Integer.parseInt(modelNumber);

                scene.getPosition(1).setModel(ModelList.modelObjects[modelIndex]);

                updateGUI();
            }
        });


        // Event handler for the combo box of color names.
        colorNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                final JComboBox<String> cb = (JComboBox<String>) e.getSource();
                final String colorString = ((String) cb.getSelectedItem());
                colorChoice = getColorNumber(colorString);

                updateGUI();
            }
        });


        // Event handler for the button group of axes.
        xAxisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAxis = 0;
                axisSlider.setValue(xAxisSliderValue);

                updateGUI();
            }
        });

        yAxisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAxis = 1;
                axisSlider.setValue(yAxisSliderValue);

                updateGUI();
            }
        });

        zAxisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAxis = 2;
                axisSlider.setValue(zAxisSliderValue);

                updateGUI();
            }
        });

        // Event handler for the slider.
        axisSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                switch (currentAxis) {
                    case 0 -> { xAxisSliderValue = source.getValue(); }
                    case 1 -> { yAxisSliderValue = source.getValue(); }
                    case 2 -> { zAxisSliderValue = source.getValue(); }
                }

                updateGUI();
            }
        });


        // Define the east panel event handlers (six of them).
        toggleAxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.getPosition(0).visible = !scene.getPosition(0).visible;

                updateGUI();
            }
        });

        toggleClipping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rasterize.doClipping = !Rasterize.doClipping;
            }
        });

        perspectiveProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene = scene.changeCamera(Camera.projPerspective());

                updateGUI();
            }
        });

        orthographicsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene = scene.changeCamera(Camera.projOrtho());

                updateGUI();
            }
        });

        toggleDebug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.debug = !scene.debug;

                updateGUI();
            }
        });

        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(scene);
            }
        })

        screenshot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final FrameBuffer fb = fbp.getFrameBuffer();
                fb.dumpFB2File(String.format("Screenshot%03d.ppm", screenshotNumber));
                System.out.println(String.format("Saved screenshot%03d", screenshotNumber));
                screenshotNumber++;

                updateGUI();
            }
        });

        updateGUI();

    }

    private int getColorNumber(String colorString) {
        switch (colorString) {
            case "Red" -> { return 0; }
            case "Green" -> { return 1; }
            case "Blue" -> { return 2; }
            case "Orange" -> { return 3; }
            case "Cyan" -> { return 4; }
            case "Magenta" -> { return 5; }
            case "Black" -> { return 6; }
            case "Random Color" -> { return (int)(Math.random() * (6 - 1)); }
            case "Random Edge Color" -> { return 8; }
            case "Random Vertex Color" -> { return 9; }
            default -> { return 2; }
        }
    }

    private Color getColor(int colorIdx) {
        switch (colorIdx) {
            case 0 -> { return Color.red; }
            case 1 -> { return Color.green; }
            case 2 -> { return Color.blue; }
            case 3 -> { return Color.orange; }
            case 4 -> { return Color.cyan; }
            case 5 -> { return Color.magenta; }
            case 6 -> { return Color.black; }
            default -> { return Color.blue; }
        }
    }

    // This is essentially the View part of MVC.
    private void updateGUI() {
        // Use colorChoice to set the color of the model.
        if (colorChoice <= 6) {
            ModelShading.setColor(scene.getPosition(1).getModel(), getColor(colorChoice));  // colorChoice == 2
        } else if (colorChoice == 7) {
            ModelShading.setRandomVertexColors(scene.getPosition(1).getModel());
        } else {
            ModelShading.setRainbowPrimitiveColors(scene.getPosition(1).getModel());
        }

        // Use the slider values to position the model.
        /* Map values on the interval [0,100] to the interval [-2.0, 2.0]
           For the zAxis map the values on the interval [0,100] to [-22.0,-2.0] */
        double inMin = 0.0, inMax = 100.0, outMax = 2.0, outMin = -2.0;
        double newX = ((xAxisSliderValue - inMin) / (inMax - inMin)) * (outMax - outMin) + outMin;
        double newY = ((yAxisSliderValue - inMin) / (inMax - inMin)) * (outMax - outMin) + outMin;
        double newZ = ((zAxisSliderValue - inMin) / (inMax - inMin)) * (-22.0 - -2.0) + -2.0;

        final Vector v = scene.getPosition(1).getTranslation();
        final double xDiff = newX - v.x;
        final double yDiff = newY - v.y;
        final double zDiff = newZ - v.z;
        scene.getPosition(1).translate(v.x + xDiff, v.y + yDiff, v.z + zDiff);

        // Render again.
        final FrameBuffer fb = fbp.getFrameBuffer();
        fb.clearFB();
        Pipeline.render(scene, fb);
        fbp.repaint();
        System.out.flush(); // Because System.out is buffered by renderer.pipeline.PipelineLogger
    }

    /**
     * Create an instance of this class which has
     * the affect of creating the GUI application.
     */
    public static void main(String[] args) {
        // We need to call the program's constructor in the
        // Java GUI Event Dispatch Thread, otherwise we get a
        // race condition between the constructor (running in
        // the main() thread) and the very first ComponentEvent
        // (running in the EDT).
        javax.swing.SwingUtilities.invokeLater(
                () -> new Hw3()
        );
    }
}
