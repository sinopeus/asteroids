package entity;

import vector.Direction;
import vector.Position;
import vector.Velocity;
import entity.ship.Mass;

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
	 * @param	speedLimit
	 * 			The given speed limit.
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
	 * @effect	The speed limit of this asteroid is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @effect	The velocity of this asteroid is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this asteroid is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this asteroid is set to the its radius times the fixed density for asteroids.
	 * 			| setMass(shape.getRadius() * density)
	 * @throws	IllegalArgumentException
	 * 			| Any of the parameters is null.
	 */
	public Asteroid(Direction direction, Position position, double speedLimit, Velocity velocity, CircleShape shape) throws IllegalArgumentException
	{
		super(direction, position, speedLimit, velocity, shape, new Mass((4 * Math.PI * Math.pow(shape.getRadius(), 3) * density) / 3));
	}

	/**
	 * A variable registering the fixed density of all asteroids.
	 */
	private static double density = 2.65E12;
}
