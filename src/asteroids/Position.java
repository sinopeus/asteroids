package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class implementing simple positional information in two dimensions for
 * objects in the game.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Position extends Vector {
    
    /**
     * Sets the x component of this position to the given x component.
     * 
     * @param   x
     *          The new x-component for this velocity.
     * @post    If this velocity can have the given x-component as its x-component,
     *          then the x-component of this velocity is now equal to the given
     *          x-component. | if canHaveAsXComponent(vx) | then
     *          | new.getXComponent() == vx
     */
    @Basic
    @Raw
    public void setXComponent(double x)
    {
        if (!canHaveAsComponent(x)) {
            throw new IllegalArgumentException("Invalid vector component provided");
        } 
        else 
        {
            this.x = x;
        }
    }
    
    /**
     * Sets the y component of this position to the given y component.
     * 
     * @param   y
     *          The new y-component for this velocity.
     * @post    If this velocity can have the given y-component as its y-component,
     *          then the y-component of this velocity is now equal to the given
     *          y-component. | if canHaveAsYComponent(vy) | then
     *          | new.getYComponent() == vy
     */
    @Basic
    @Raw
    public void setYComponent(double y)
    {
        if (!canHaveAsComponent(y)) {
            throw new IllegalArgumentException("Invalid vector component provided");
        } 
        else 
        {
            this.y = y;
        }
    }
    
    /**
     * Initializes this new Position with a given x-coordinate and y-coordinate.
     * 
     * @param   x
     *          The given x coordinate
     * @param   y
     *          The given y coordinate
     */
    public Position(double x,double y)
    {
        super(x,y);
    }
    
    /**
     * Initialize this new position with a given vector
     * 
     * @param	v
     * 			The given vector.
     */
    public Position(Vector v){
    	super(v);
    }
    
    /**
     * {@inheritDoc}
     * @see Vector#Position()
     */
    public Position()
    {
        super();
    }
    
    /**
     * Gets the sum of this Position and the given vector.
     * 
     * @param	v
     * 			The given vector.
     * @return	The sum of this position and the given vector.
     * 			| result = new Position(super.getSum(v))
     */
    @Override
    public Position getSum(Vector v)
    {
    	return new Position(super.getSum(v));
    }
    
    /**
     * Moves this position using a given velocity during a given duration.
     * 
     * @param	v
     * 			The given velocity.
     * @param	duration
     * 			The given duration.
     * @throws	IllegalArgumentException
     * 			The given duration is strictly negative.
     * 			| duration < 0
     * @throws	IllegalArgumentException
     * 			Any of the resulting components is not a valid component.
     * @see		#canHaveAsComponent(double)
     * @post	Moves this position to the calculated destination.
     * 			| new.equals(getSum(v.scaleBy(duration)))
     */
    public void moveBy(Velocity v, double duration) throws IllegalArgumentException{
		if (duration < 0)
		{
			throw new IllegalArgumentException("Invalid duration provided");
		}
    	Position p = getSum(v.scaleBy(duration));
    	setXComponent(p.getXComponent());
    	setYComponent(p.getYComponent());
    }
}
