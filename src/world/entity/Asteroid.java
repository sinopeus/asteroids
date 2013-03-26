package world.entity;

import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * TODO document
 */
public class Asteroid extends Entity
{
	/**
	 * Initializes this new asteroid with a given direction, position, shape, speed limit and velocity.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @param	position
	 * 			The given position.
	 * @param	shape
	 * 			The given shape.
	 * @param	velocity
	 * 			The given velocity.
	 * @param	shape
	 * 			The given circle shape
	 * @effect	The direction of this asteroid is set to the given direction.
	 * 			| setDirection(direction)
	 * @effect	The position of this asteroid is set to the given position.
	 * 			| setPosition(position)
	 * @effect	The shape of this asteroid is set to the given shape.
	 * 			| setShape(shape)
	 * @effect	The velocity of this asteroid is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this asteroid is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this asteroid is set to the its radius times the fixed density for asteroids.
	 * 			| setMass(shape.getRadius() * density)
	 * @throws	IllegalArgumentException
	 * 			| Any of the parameters is null.
		this.shooter = null;
	
	 */
	public Asteroid (Direction direction, Position position, Velocity velocity, CircleShape shape) throws IllegalArgumentException
	{
		super (direction, position, Velocity.getSpeedOfLight (), velocity, shape, new Mass ( (4 * Math.PI * Math.pow (shape.getRadius (), 3) * density) / 3));
	}

	//TODO DOCUMENT & TEST
	@Override
	public void terminate ()
	{
		if (getShape ().getRadius () >= 30) //TODO MAKE GETTER FOR THIS
		{
			Direction d1 = new Direction (new Angle (getDirection ().getAngle ().get ()));
			Direction d2 = new Direction (new Angle (getDirection ().getAngle ().get () + Math.PI));
			Position p1 = new Position (getPosition ().getSum (d1.getScaledBy (getShape ().getRadius () / 2)));
			Position p2 = new Position (getPosition ().getSum (d2.getScaledBy (getShape ().getRadius () / 2)));
			Velocity v1 = new Velocity (getVelocity ().getScaledBy (1.5));
			Velocity v2 = new Velocity (getVelocity ().getScaledBy (-1.5));
			CircleShape s1 = new CircleShape (getShape ().getRadius () / 2);
			CircleShape s2 = new CircleShape (getShape ().getRadius () / 2);

			Asteroid a1 = new Asteroid (d1, p1, v1, s1);
			Asteroid a2 = new Asteroid (d2, p2, v2, s2);

			getWorld ().add (a1);
			getWorld ().add (a2);
		}
		super.terminate ();
		}
	
	@Override
	public void advance (double dt)
	{
		this.turn (new Angle(Math.PI/7.0));//TODO MAKE GETTER FOR THIS
		super.advance (dt);
	}

	@Override
	public String toString ()
	{
		return "asteroid_" + hashCode () + " at " + getPosition ();
	}

	/**
	 * A variable registering the fixed density of all asteroids.
	 */
	private static double	density	= 2.65E12;
}
