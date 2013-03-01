package asteroids;

public class Acceleration extends Vector
{
	/**
	 * Initializes this new acceleration with a given x component and y component.
	 * 
	 * @param	x
	 * 			The given x component.
	 * @param 	y
	 * 			The given y component.
	 */
	public Acceleration(double x, double y){
		super(x,y);
	}
	
	/**
	 * Initializes this new acceleration with a given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 */
	public Acceleration(Vector v){
		super(v.getXComponent(),v.getYComponent());
	}
	
	/**
	 * Initializes this new acceleration with default values.
	 */
	public Acceleration(){
		super();
	}
}