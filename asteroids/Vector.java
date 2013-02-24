package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A superclass for vectorial properties, such as position, velocity,
 * acceleration, direction... 
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

   /**
    * A method for transparently resizing the vector's magntitude by a real
    * factor.
    */

   protected void rescale(double scalar) {
       this = this.scalarProduct(scalar);
   }

   /** 
    * Return basic properties of the vector.  
    **/

   protected double getMagnitude() {
       return Math.sqrt(this.innerProduct(this));
   }

   protected Vector getDirection() {
       return this.scalarProduct(1.0 / this.getMagnitude());
   }

   /**
    * Calculate the angle of the directional vector with the x axis.
    */

   protected Angle getAngle() {
       Vector v = this.getDirection();
       return Math.acos(v.getXComponent);
   }

   /**
    * Scalar operators.  
    **/

   protected Vector scalarProduct(double scalar) {
       return new Vector(x * scalar, y * scalar);
   }

   /**
    * Vectorial operators.
    */

   protected double getDistanceTo(Vector v) {
       return this.vectorAddition(v).getMagnitude();
   }

   protected double innerProduct(Vector v) {
       return (x * v.getXComponent()) + (y * v.getYComponent());
   }

   /**
    * Generic constructors.
    */

   protected Vector(double x, double y) {
       setXComponent(x);
       setYComponent(y);
   }

   protected Vector() {
       this(0, 0);
   }

}
