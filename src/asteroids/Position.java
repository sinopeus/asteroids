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
	 * @param 	x
	 *          The new x-component for this velocity.
	 * @post 	If this velocity can have the given x-component as its x-component,
	 *       	then the x-component of this velocity is now equal to the given
	 *       	x-component. | if canHaveAsXComponent(vx) | then
	 *       	| new.getXComponent() == vx
	 */
	@Basic
	@Raw
	public void setXComponent(double x)
	{
		if (!canHaveAsComponent(x)) {
            throw new IllegalArgumentException("Invalid vector component provided");
        } 
        else {
			this.x = x;
		}
	}
	
	/**
	 * Sets the y component of this position to the given y component.
	 * 
	 * @param	y
	 *          The new y-component for this velocity.
	 * @post 	If this velocity can have the given y-component as its y-component,
	 *       	then the y-component of this velocity is now equal to the given
	 *       	y-component. | if canHaveAsYComponent(vy) | then
	 *       	| new.getYComponent() == vy
	 */
	@Basic
	@Raw
	public void setYComponent(double y)
	{
		if (!canHaveAsComponent(y)) {
            throw new IllegalArgumentException("Invalid vector component provided");
        } 
        else {
			this.y = y;
		}
	}
	
	/**
	 * Initializes this new Position with a given x-coordinate and y-coordinate.
	 * 
	 * @param	x
	 * 			The given x coordinate
	 * @param	y
	 * 			The given y coordinate
	 */
	public Position(double x,double y){
		super(x,y);
	}
	
	/**
	 * Initializes this new Position at (0,0)
	 */
	public Position(){
		super();
	}
}
