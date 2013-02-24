package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A superclass for vectorial properties, such as position, velocity,
 * acceleration, direction... Implemented for the sake of economy.
 *
 *
 * @author Syd & Xavier
 * @author
 * @version 0.0
 */

public class Vector {

    /**
     * Gets the x component of the vector.
     *
     * @return The x component of the vector as a double precision floating point number.
     */
    protected double getXComponent() {
       return this.x;
   }
   

    /**
     * Sets the x component of the vector.
     *
     * @param x The chosen double precision floating point value for the x component of the vector.
     */
    protected void setXComponent(double x) {
        this.x = x;
    }

    /**
     * A variable for storing the x component of the vector.
     */
    protected double x;


    /**
     * Gets the y component of the vector.
     *
     * @return The y component of the vector as a double precision floating point number.
     */
    protected double getYComponent() {
       return this.y;
   }

    /**
     * Sets the y component of the vector.
     *
     * @param y The chosen double precision floating point value for the y component of the vector.
     */
    protected void setYComponent(double y) {
        this.y = y;
    }

    /**
     * A variable for storing the x component of the vector.
     */
    protected double y;


    /**
     * Generic boolean for checking if the input can be a vector component.
     *
     * @param x A double precision floating point number. The method will throw an exception if it isn't a double.
     * @return A boolean indicating whether the formal argument is a possible value for a vector component.
     */
    protected boolean canHaveAsComponent(double x) {
       return (true);
   }

   /**
    * Methods implementing the basic operations on elements of commutative groups.
    */

    /**
     * A method which defines vectorial addition.
     *
     * @param v An instance of the class Vector which we want to add to the instance on which it is called.
     * @return A new instance of the class Vector whose components are the sums
     * of the respective components of the two Vector objects given in the
     * implicit and explicit parameter.
     */
    protected Vector sum(Vector v) {
       //TODO
   }

    /**
     * A method which defines vectorial subtraction.
     *
     * @param v An instance of the class Vector which we want to subtract from the instance on which it is called.
     * @return A new instance of the class Vector whose components are the
     * differences of the respective components of the two Vector objects given
     * in the implicit and explicit parameter.
     */
    protected Vector difference(Vector v) {
       //TODO
   }

    /**
     * Methods implementing functions for commutative groups equipped with scalar multiplication.
     */

    /**
     * Takes a double precision floating point number and scales the Vector
     * object on which it is called by that factor.
     *
     * @param scalar
     * @return A new Vector object with each of the components scaled by the
     * factor provided in the explicit parameter.
     */
    protected Vector scale(double scalar) {
       //TODO
   }
    /**
     * Methods implementing basic operations on inner product spaces.
     */

    /**
     * A method which returns the inner product of two vectors.
     *
     * @param v A Vector object of which we want to compute the inner product with the Vector object upon which the method is called.
     * @return A double precision floating point value representing the inner product of the Vector objects in the implicit and explicit parameters.
     */
    protected double dot(Vector v) {
       //TODO
   }

    /**
     * A method which returns the magnitude of a vector.
     *
     * @param v A Vector object whose magnitude we want to compute.
     * @return A double precision floating point value representing the magnitude of the Vector object.
     */
    protected double magnitude(Vector v) {
        //TODO
    }
   
    /**
     * Constructors for the vector class.
     * @param x The x component of the vector we want to construct.
     * @param y The y component of the vector we want to construct.
     **/

   protected Vector(double x, double y) {
       setXComponent(x);
       setYComponent(y);
   }

    /**
     * A constructor for the zero vector.
     *
     * {@inheritDoc}
     * @see Object#Vector()
     */
    protected Vector() {
       this(0, 0);
   }

}
