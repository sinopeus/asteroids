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

   protected double getXComponent() {
       return this.x;
   }
   

   protected void setXComponent(double x);

   protected double x;


   protected double getYComponent() {
       return this.y;
   }

   protected void setYComponent(double y);

   protected double y;


   protected boolean canHaveAsComponent(double x) {
       return (true);
   }

   protected double getEuclideanNorm() {
		return Math.sqrt(Math.pow(this.getXComponent(), 2) + Math.pow(this.getYComponent(), 2));
   }

   protected Vector(double x, double y) {
       setXComponent(x);
       setYComponent(y);
   }

   protected Vector() {
       this(0, 0);
   }

}
