package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A superclass for vectorial properties, such as position and velocity. 
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */

public abstract class Vector {

   protected double getXComponent();
   protected boolean canHaveAsXComponent(double x);
   protected void setXComponent(double x);

   protected double x;

   protected double getYComponent();
   protected boolean canHaveAsYComponent(double y);
   protected void setYComponent(double y);

   protected double y;

   protected Vector(double x, double y) {
       setXComponent(x);
       setYComponent(y);
   }

   protected Vector() {
       this(0, 0);
   }

}
