/*

*/

import renderer.scene.util.Assets;
import renderer.scene.util.ModelShading;
import renderer.scene.Model;
import renderer.models_L.*;

import java.io.File;
import java.awt.Color;

/**
   This class provides the arrays needed by the JComboBox
   that lists the model names.
   <p>
   The array modelNames should be passed to the JComboBox
   constructor so that the combo box has all the correct
   model names.
   <p>
   The array modelObjects is parallel to modelNames. The
   modelObjects array contains all the Model objects, each
   at the same index as its name in modelNames. So your code
   does not need to instantiate any of these models. They are
   all instantiated by the (static) code in this class.
   <p>
   Notice that every model name is prefaced with a model
   number. This makes it easy for you to use the model's
   name from the JComboBox to derive the model object's
   index in the modelObjects array.
*/
public class ModelList
{
   public static String[] modelNames = {
      // 2D models
      "01 - Square",
      "02 - SquareGrid",
      "03 - Circle",
      "04 - CircleSector",
      "05 - CircleSector",
      "06 - Disk",
      "07 - DiskSector",
      "08 - Ring",
      "09 - RingSector",
      "10 - Barycentric Triangle",
      // cubes
      "11 - Box",
      "12 - Cube",
      "13 - Cube2(4,5,6)",
      "14 - Cube2(40,50,60)",
      "15 - Cube3(12,14,15)",
      "16 - Cube4(12,14,15)",
      // polyhedra
      "17 - Tetrahedron(30,30,30,30)",
      "18 - Octahedron(30)",
      "19 - Octahedron(20,20,20)",
      "20 - Dodecahedron",
      "21 - Icosahedron",
      "22 - Icosidodecahedron",
      // pyramids
      "23 - Pyramid",
      "24 - Pyramid Frustum",
      "25 - Triangular Pyramid",
      // cones
      "26 - Cone",
      "27 - ConeFrustum",
      // cylinder
      "28 - Cylinder",
      "29 - CylinderSector",
      // spheres
      "30 - Sphere",
      "31 - SphereSubdivided 4",
      "32 - SphereSubdivided 6",
      "33 - SphereSubdivided 7",
      // torus
      "34 - Torus",
      "35 - TorusSector",
      // model files
      "36 - GRS Model bronto",
      "37 - Obj Model, cow",
      "38 - Obj Model, cessna",
      "39 - Obj Model, apple",
      "40 - Obj Model, teapot",
      "41 - Obj Model, bunny",
      // parametric curves and surfaces
      "42 - ParametricCurve",
      "43 - ParametricSurface 1",
      "44 - ParametricSurface 2",
      "45 - ParametricSurface 3",
      "46 - SurfaceOfRevolution 1",
      "47 - SurfaceOfRevolution 2",
      "48 - SurfaceOfRevolution 3"
   };


   private static final String assets = Assets.getPath();


   public static Model[] modelObjects = {
      // 2D models
      new Square(1.0),                                         // 1
      new SquareGrid(1.0, 11, 15),                             // 2
      new Circle(1.0, 16),                                     // 3
      new CircleSector(1.0,  Math.PI/4, -Math.PI/4, 13),       // 4
      new CircleSector(1.0, -Math.PI/4,  Math.PI/4,  5),       // 5
      new Disk(1.0, 4, 16),                                    // 6
      new DiskSector(1.0, Math.PI/2, 3*Math.PI/2, 4, 8),       // 7
      new Ring(1.0, 0.25, 3, 16),                              // 8
      new RingSector(1.0, 0.25, Math.PI/2, 3*Math.PI/2, 3, 8), // 9
      new BarycentricTriangle(45, 4),                          // 10
      // cubes
      new Box(1.0, 1.0, 1.0),                      // 11
      new Cube(),                                  // 12
      new Cube2(4, 5, 6),                          // 13
      new Cube2(40, 50, 60),                       // 14
      new Cube3(12, 14, 15),                       // 15
      new Cube4(12, 14, 15),                       // 16

      // polyhedra
      new Tetrahedron(30, 30, 30, 30),             // 17
      new Octahedron(30),                          // 18
      new Octahedron(20, 20, 20),                  // 19
      new Dodecahedron(),                          // 20
      new Icosahedron(),                           // 21
      new Icosidodecahedron(),                     // 22
      // pyramids
      new Pyramid(2.0, 1.0, 5, 6),                 // 23
      new PyramidFrustum(2.0, 1.0, 0.5, 4, 5),     // 24
      new TriangularPyramid(1.0, 1.0, 7, 7),       // 25
      // cones
      new Cone(1.0, 1.0, 10, 16),                  // 26
      new ConeFrustum(1.0, 0.5, 0.5, 6, 16),       // 27
      // cylinder
      new Cylinder(0.5, 1.0, 11, 12),              // 28
      new CylinderSector(
         0.5, 1.0, Math.PI/2, 3*Math.PI/2, 11, 6), // 29
      // spheres
      new Sphere(1.0, 15, 12),                  // 30
      new SphereSubdivided(4),                  // 31
      new SphereSubdivided(6, true, false),     // 32
      new SphereSubdivided(7, false, true),     // 33
      // torus
      new Torus(0.75, 0.25, 12, 16),            // 34
      new TorusSector(0.75, 0.25, 0, 2*Math.PI, Math.PI, 2*Math.PI, 6, 16), // 35
      // model files
      new GRSModel(new File(assets + "grs/bronto.grs")),           // 36
      new ObjSimpleModel(new File(assets + "cow.obj")),            // 37
      new ObjSimpleModel(new File(assets + "cessna.obj")),         // 38
      new ObjSimpleModel(new File(assets + "apple.obj")),          // 39
      new ObjSimpleModel(new File(assets + "teapot.obj")),         // 40
      new ObjSimpleModel(new File(assets + "stanford_bunny.obj")), // 41
      // parametric curves and surfaces
      new ParametricCurve(),                                       // 42
      new ParametricSurface(),                                     // 43
      new ParametricSurface(                                       // 44
                (s,t) -> s*Math.cos(t*Math.PI),
                (s,t) -> t,
                (s,t) -> s*Math.sin(t*Math.PI),
                -1, 1, -1, 1, 49, 49),
      new ParametricSurface(                                       // 45
                (u,v) -> 0.3*(1-u)*(3+Math.cos(v))*Math.sin(4*Math.PI*u),
                (u,v) -> 0.3*(3*u+(1-u)*Math.sin(v)),
                (u,v) -> 0.3*(1-u)*(3+Math.cos(v))*Math.cos(4*Math.PI*u),
                0, 1, 0, 2*Math.PI, 49, 49),
      new SurfaceOfRevolution(),                                   // 46
      new SurfaceOfRevolution(                                     // 47
                t -> 0.5*(1+t*t), -1, 1, 30, 30),
      new SurfaceOfRevolution(                                     // 48
                t -> t, t->4*t*(1-t), 0, 1, 30, 30)
   };


   // A static initializer that gives every model the color black.
   static
   {
      for (Model m : modelObjects)
      {
         ModelShading.setColor(m, Color.black);
      }
   }
}
