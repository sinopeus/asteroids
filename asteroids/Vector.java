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

   protected void rescale(double scalar) {
       this = this.scalarProduct(scalar);
   }

   protected boolean canHaveAsComponent(double x) {
       return (true);
   }

   protected double getMagnitude() {
       return Math.sqrt(this.innerProduct(this));
   }

   protected Vector getDirection() {
       return this.scalarProduct(1.0 / this.magnitude());
   }

   protected Angle getAngle() {
       Vector v = this.getDirection();
       return Math.acos(v.getXComponent);
   }

   protected Vector scalarProduct(double scalar) {
       return new Vector(x * scalar, y * scalar);
   }

   protected double innerProduct(Vector v) {
       return (x * v.getXComponent()) + (y * v.getYComponent());
   }

   protected Vector(double x, double y) {
       setXComponent(x);
       setYComponent(y);
   }

   protected Vector() {
       this(0, 0);
   }

}
