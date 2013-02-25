package asteroids;

/**
 * A class for storing information on the orientation of the spaceship.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Direction extends Vector
{

	public Direction(Angle angle)
	{
		setXComponent(angle.cos());
		setYComponent(angle.sin());
	}

}