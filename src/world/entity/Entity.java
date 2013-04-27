package world.entity;

import world.World;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.collision.Border;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import world.physics.vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of entities involving a position, velocity, direction, speed limit.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 *
 * @invar	The direction of this ship is a valid direction.
 * 			| canHaveAsDirection(getDirection())
 * @invar	The position of this ship is a valid position.
 * 			| canHaveAsPosition(getPosition())
 * @invar	The shape of this ship is a valid shape.
 * 			| canHaveAsShape(getShape())
 * @invar	The speedLimit of this ship is a valid speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar	The velocity of this ship is a valid velocity.
 * 			| canHaveAsVelocity(getVelocity())
 */
public class Entity
{
	/**
	 * Initializes this new entity with a given direction, position, shape, mass, speed limit and velocity.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @param	position
	 * 			The given position.
	 * @param	speedLimit
	 * 			The given speed limit.
	 * @param	velocity
	 * 			The given velocity
	 * @param	shape
	 * 			The given circle shape
	 * @param	mass
	 * 			The given mass
	 * @effect	The direction of this entity is set to the given direction.
	 * 			| setDirection(direction)
	 * @effect	The position of this entity is set to the given position.
	 * 			| setPosition(position)
	 * @effect	The speed limit of this entity is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @effect	The velocity of this entity is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this entity is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this entity is set to the given mass.
	 * 			| setMass(mass)
	 * @throws	IllegalArgumentException
	 * 			| Any of the parameters is null.
	 * @throws	IllegalArgumentException
	 * 			| The provided shape is not valid.
	 */
	public Entity (Direction direction, Position position, double speedLimit, Velocity velocity, CircleShape shape, Mass mass) throws IllegalArgumentException
	{
		setDirection(direction);
		setPosition(position);
		setSpeedLimit(speedLimit);
		setVelocity(velocity);
		if (!canHaveAsShape(shape)) throw new IllegalArgumentException("Invalid circle shape provided");
		else this.shape = shape;
		setMass(mass);
		isTerminated = false;
	}

	/**
	 * Initializes this new entity with default values.
	 * 
	 * @effect	Initializes this new entity with the extended entity constructor and default values.
	 * 			| this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity())
	 */
	public Entity ()
	{
		this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(40), new Mass(5E15));
	}

	/**
	 * Gets the position of this entity.
	 */
	@Basic
	@Raw
	public Position getPosition ()
	{
		return this.position;
	}

	/**
	 * Checks whether this entity can have the given position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the given position is not null.
	 * 			| result == (position != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsPosition (Position position)
	{
		return (position != null);
	}

	/**
	 * Sets the position of this entity to the given position.
	 *
	 * @param	position
	 *			The new position for this entity.
	 * @post	The position of this entity is now equal to the given position.
	 * 			| new.getPosition() == position
	 * @throws	IllegalArgumentException
	 * 			The this entity can't have the given position as its position,.
	 * 			| !canHaveAsPosition(position)
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setPosition (Position position) throws IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (!canHaveAsPosition(position))
		{
			throw new IllegalArgumentException("Invalid position provided.");
		} else
		{
			this.position = position;
		}
	}

	/**
	 * A variable referencing the position of this entity.
	 */
	protected Position	position;

	/**
	 * Gets the velocity of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Velocity getVelocity ()
	{
		return this.velocity;
	}

	/**
	 * Checks whether this entity can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the given velocity is not null and its magnitude is at most the speed limit.
	 * 			| result = ((velocity != null) && (velocity.getVelocity() <= getSpeedLimit()))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsVelocity (Velocity velocity)
	{
		return ( (velocity != null) && (velocity.get() <= getSpeedLimit()));
	}

	/**
	 * Sets the velocity of this entity to the given velocity.
	 *
	 * @param	velocity
	 *			The new velocity for this entity.
	 * @post	If this entity can have the given velocity as its velocity,
	 * 			then the velocity of this entity is now equal to the given velocity.
	 * 			| if canHaveAsVelocity(velocity)
	 * 			|	then new.getVelocity() == velocity
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setVelocity (Velocity velocity) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (canHaveAsVelocity(velocity))
		{
			this.velocity = velocity;
		}
	}

	/**
	 * A variable referencing the velocity of this entity.
	 */
	protected Velocity	velocity;

	/**
	 * Gets the direction of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Direction getDirection ()
	{
		return this.direction;
	}

	/**
	 * Checks whether this entity can have the given direction as its direction.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the direction is not null.
	 * 			| result == (direction != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsDirection (Direction direction)
	{
		return (direction != null);
	}

	/**
	 * Sets the direction of this entity to the given direction.
	 *
	 * @param	direction
	 *			The new direction for this entity.
	 * @pre		The given direction must be a valid direction.
	 * 			| canHaveAsDirection(direction)
	 * @post	The direction of this entity is now equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setDirection (Direction direction) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		assert (canHaveAsDirection(direction));
		this.direction = direction;
		assert (getDirection().equals(direction));
	}

	/**
	 * A variable referencing the direction of this entity.
	 */
	protected Direction	direction;

	/**
	 * Gets a shape equal to the shape of this ship.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public CircleShape getShape ()
	{
		return new CircleShape(shape.getRadius());
	}

	/**
	 * Checks whether this ship can have the given shape as its shape.
	 * 
	 * @param 	shape
	 * 			The shape to check.
	 * @return	True if and only if the given shape is not null and has a range of at least the minimum radius for ships.
	 * 			| result = (shape != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsShape (@Raw CircleShape shape)
	{
		return (shape != null);
	}

	/**
	 * A variable referencing the shape of this ship.
	 */
	private final CircleShape	shape;

	/**
	 * Returns the speed limit of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double getSpeedLimit ()
	{
		return this.speedLimit;
	}

	/**
	 * Checks whether this entity can have the given speed limit as its speed limit.
	 * 
	 * @param 	speedLimit
	 * 			The speed limit to check.
	 * @return	True if and only if the given speed limit is positive and at most the speed of light.
	 * 			| result = ((speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsSpeedLimit (double speedLimit)
	{
		return ( (speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()));
	}

	/**
	 * Sets the speed limit of this entity to the given speed limit.
	 *
	 * @param	speedLimit
	 *			The new speed limit for this entity.
	 * @post	If this entity can have the given speed limit as its speed limit,
	 * 			then the speed limit of this entity is now equal to the given speed limit.
	 * 			| if canHaveAsSpeedLimit(speedLimit)
	 * 			|	then new.getSpeedLimit() == speedLimit
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setSpeedLimit (double speedLimit) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (canHaveAsSpeedLimit(speedLimit))
		{
			this.speedLimit = speedLimit;
		} else
		{
			this.speedLimit = Velocity.getSpeedOfLight();
		}
	}

	/**
	 * A variable registering the speed limit of this entity.
	 */
	protected double	speedLimit;

	/**
	 * Gets the mass of this ship.
	 */
	@Basic
	@Raw
	public Mass getMass ()
	{
		return mass;
	}

	/**
	 * Checks whether this entity can have the given mass as its mass.
	 * 
	 * @param	mass
	 * 			The given mass
	 * @return	True if and only if the given mass is not null
	 * 			| mass != null
	 */
	@Basic
	@Raw
	private boolean canHaveAsMass (Mass mass)
	{
		return (mass != null);
	}

	/**
	 * Sets the mass of this entity to the given mass.
	 * @param	mass
	 * 			The given mass.
	 * @post	The mass of this ship is now equal to the given mass.
	 * 			| new.getMass() == mass
	 * @throws	IllegalArgumentException
	 * 			The given mass is not a valid mass
	 * 			| !canHaveAsMass(mass)
	 */
	@Basic
	@Raw
	private void setMass (Mass mass) throws IllegalArgumentException
	{
		if (!canHaveAsMass(mass)) { throw new IllegalArgumentException("Invalid mass provided"); }
		this.mass = mass;
	}

	/**
	 * A variable referencing the mass of this ship.
	 */
	private Mass	mass;

	/**
	 * Returns the world of this entity.
	 */
	@Basic
	@Raw
	public World getWorld ()
	{
		return this.world;
	}

	/**
	 * Checks whether this entity can have the given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is not null.
	 * 			| result = (world != null)
	 */
	@Basic
	@Raw
	private boolean canHaveAsWorld (World world)
	{
		return (world != null);
	}

	/**
	 * Sets the world of this entity to the given world.
	 *
	 * @param 	world
	 *        	The new world for this entity.
	 * @throws	IllegalArgumentException
	 * 			The given world is not a valid world
	 * 			| !canHaveAsWorld(world)
	 */
	@Basic
	@Raw
	public void setWorld (World world) throws IllegalArgumentException
	{
		if (!canHaveAsWorld(world)) { throw new IllegalArgumentException("Illegal world provided."); }
		this.world = world;
	}

	protected World	world;

	/**
	 * Checks whether this entity is terminated.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public boolean isTerminated ()
	{
		return this.isTerminated;
	}

	/**
	 * Terminates this entity.
	 * 
	 * @effect	Removes this entity from the world.
	 * 			| getWorld().remove(this);
	 * @post	Makes the reference to the world null.
	 * 			| new.getWorld() == null
	 * @post	This entity is now terminated.
	 * 			| isTerminated()
	 */
	public void terminate ()
	{
		getWorld().remove(this);
		this.world = null;
		isTerminated = true;
	}

	/**
	 * A variable registering whether this entity is terminated.
	 */
	protected boolean	isTerminated;

	/**
	 * Moves this entity over the given time interval. 
	 * 
	 * @param 		dt
	 * 				The time interval for the ship's movement.
	 * @effect		The entity's position vector is altered, taking into account its direction, velocity, and a given time interval.
	 * 				| move(dt)
	 */
	public void advance (double dt)
	{
		move(dt);
	}

	/**
	 * Changes the position of this entity based on the current position, velocity and a given duration of the movement.
	 * 
	 * @param	duration
	 * 			The given duration of the movement.
	 * @effect	Sets the position of this entity to the position after moving.
	 * 			| getPosittion().moveBy(getVelocity(), duration)
	 * @throws	ArithmeticException
	 * 			Any of the resulting vector components is not a number.
	 * 			| ((Double.isNaN(getPosition.getXComponent()) || Double.isNaN(getPosition.getYComponent()))
	 * @throws	IllegalArgumentException
	 * 			The given duration is strictly negative.
	 * 			| duration <= 0
	 * @throws	IllegalStateException
	 * 			| isTerminated()	
	 */
	public void move (double duration) throws ArithmeticException, IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (duration < 0) duration = 0;
		this.position.moveBy(getVelocity(), duration);
	}

	/**
	 * Has this entity collide with the given entity
	 * 
	 * @param	that
	 * 			The given entity
	 * @effect	Has this entity collide with the given entity
	 * 			| this.collideWith((that.getClass())that) //TODO
	 */
	public void collideWith (Entity that)
	{
		if (that == null) return;
		if (that instanceof Bullet) this.collideWith((Bullet) that);
		else if (that instanceof Asteroid) this.collideWith((Asteroid) that);
		else if (that instanceof Ship) this.collideWith((Ship) that);
	}

	/**
	 * Has this entity collide with the given ship.
	 * 
	 * @param	that
	 * 			The given ship.
	 * @effect	Has this entity bounce with the given ship.
	 * 			| this.bounce(that);
	 */
	protected void collideWith (Ship that)
	{
		if (that == null) return;
		this.bounce(that);
	}

	/**
	 * Has this entity collide with the given asteroid.
	 * 
	 * @param	that
	 * 			The given asteroid.
	 * @effect	Has this entity bounce with the given asteroid.
	 * 			| this.bounce(that);
	 */
	protected void collideWith (Asteroid that)
	{
		if (that == null) return;
		this.bounce(that);
	}

	/**
	 * Has this entity collide with the given bullet.
	 * 
	 * @param	that
	 * 			The given bullet.
	 * @effect	Has this entity bounce with the given bullet.
	 * 			| this.bounce(that);
	 */
	protected void collideWith (Bullet that)
	{
		if (that == null) return;
		this.bounce(that);
	}

	/**
	 * Has this entity collide with the given border.
	 * 
	 * @param	that
	 * 			The given border.
	 */
	public void collideWith (Border that)
	{
		if (that == null) return; //One case for every boundary to hit
		switch (that)
		{
			case BORDER_TOP:
			case BORDER_BOTTOM:
				getVelocity().setY(-getVelocity()._Y());
				break;
			case BORDER_RIGHT:
			case BORDER_LEFT:
				getVelocity().setX(-getVelocity()._X());
				break;
		}
	}

	/**
	 * Changes the direction of this entity based on the current angle and a given angle.
	 * 
	 * @param	angle
	 * 			The given angle
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @effect	The angle of this entity is modified by the given angle.
	 * 			| this.direction.rotate(angle);
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	public void turn (Angle angle) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		assert (angle != null);
		this.direction.rotate(angle);
	}

	/**
	 * Bounces this entity off another according to the classical laws of physics.
	 * 
	 * @param	that 
	 * 			The entity off which this entity bounces.
	 * @post	The velocity vectors of both entities are modified in accordance with the principles of elastic collision.
	 * 			| //TODO
	 */
	public void bounce (Entity that) 
	{
		try
		{
			Position p1 = this.getPosition();
			Position p2 = that.getPosition();
			Velocity v1 = this.getVelocity();
			Velocity v2 = that.getVelocity();
			double m1 = this.getMass().get();
			double m2 = that.getMass().get();

			//--VELOCITY CALCULATION--
			//step 1: find the unit normal vector and the unit tangent vector.
			Vector n = p2.getDifference(p1);
			Vector un = n.getUnitVectorInDirection();
			Vector ut = new Vector(-un._Y(), un._X());

			//step 2: find the components of the initial velocity in the normal and tangent directions.
			double v1n = v1.dotProduct(un);
			double v1t = v1.dotProduct(ut);
			double v2n = v2.dotProduct(un);
			double v2t = v2.dotProduct(ut);

			//step 3: the velocity remains the same in the tangent direction.
			double v1tf = v1t;
			double v2tf = v2t;

			//step 4: the velocity in the normal is calculated as if it is a one dimensional collision.
			double v1nf = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
			double v2nf = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

			//step 5: multiply the components by their unit vector to get vectors.
			Velocity v1nVector = new Velocity(un.getScaledBy(v1nf));
			Velocity v1tVector = new Velocity(ut.getScaledBy(v1tf));
			Velocity v2nVector = new Velocity(un.getScaledBy(v2nf));
			Velocity v2tVector = new Velocity(ut.getScaledBy(v2tf));

			//step 6: add the component vectors together to get the final vectors.
			Velocity v1f = v1nVector.getSum(v1tVector);
			Velocity v2f = v2nVector.getSum(v2tVector);
			//--VELOCITY CALCULATION--

			this.setVelocity(v1f);
			that.setVelocity(v2f);
		} catch (ArithmeticException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Checks whether the given entity overlaps with this entity.
	 * 
	 * @param	e
	 * 			The given entity.
	 * @return	True if and only if the given entity is null of the distance between the entities is smaller than the sum of the radii
	 * 			| result == ((e != null) && (this.distanceTo(e) < getShape().getRadius() + e.getShape().getRadius()))
	 */
	public boolean overlapsWith (Entity e)
	{
		return ( (e != null) && (this.distanceTo(e) < getShape().getRadius() + e.getShape().getRadius()));
	}

	/**
	 * Gets the distance of this entity to the given entity.
	 * 
	 * @param	e
	 * 			The given entity.
	 * @return	The distance of this entity to the given entity.
	 * 			| result == distanceTo(e.getPosition())
	 */
	public double distanceTo (Entity e)
	{
		return distanceTo(e.getPosition());
	}

	/**
	 * Gets the distance of this entity to the given position.
	 * 
	 * @param	p
	 * 			The given position.
	 * @return	The distance of this entity to the given position.
	 * 			| result == getPosition().getDistanceTo(p)
	 */
	public double distanceTo (Position p)
	{
		return getPosition().getDistanceTo(p);
	}

	/**
	 * Return the distance between this entity and the given entity, if both
	 * would move during the given duration.
	 * 
	 * @param  	other
	 *         	The given entity
	 * @param  	duration
	 * 	       	The given duration.
	 * @return	the distance between this entity and the other entity, if both would move during the given time.
	 *       	Util.fuzzyEquals(result,
	 *       	| this.getPosition().getPositionAfterMove(getVelocity(),
	 *       	|  duration).getDistanceTo(other.getPosition().getPositionAfterMove(other.getVelocity(), duration)) - (this.getShape().getRadius() + other.getShape().getRadius());)
	 * @throws 	NullPointerException
	 *         	The given entity is null
	 *       	| other == null
	 * @throws 	IllegalArgumentException
	 *         	The given duration is strictly negative
	 *       	| moveTime < 0.0
	 */
	public double distanceBetween (Entity other, double duration) throws NullPointerException, IllegalArgumentException
	{
		if (duration < 0.0) throw new IllegalArgumentException();
		return this.getPosition().getPositionAfterMove(getVelocity(), duration).getDistanceTo(other.getPosition().getPositionAfterMove(other.getVelocity(), duration)) - (this.getShape().getRadius() + other.getShape().getRadius());
		//		return this.getPositionAfterMove(duration).distanceTo(other.getPositionAfterMove(duration))
		//				   - (this.getShape().getRadius() + other.getShape().getRadius());
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	public String toString ()
	{
		return " at " + getPosition() + "   with velocity " + getVelocity() + "   and shape " + getShape() + "  ";
	}

	//WEE SHOULDN'T USE THIS
//	@Override
//	public int hashCode ()
//	{
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ( (direction == null) ? 0 : direction.hashCode());
//		result = prime * result + (isTerminated ? 1231 : 1237);
//		result = prime * result + ( (mass == null) ? 0 : mass.hashCode());
//		result = prime * result + ( (position == null) ? 0 : position.hashCode());
//		result = prime * result + ( (shape == null) ? 0 : shape.hashCode());
//		long temp;
//		temp = Double.doubleToLongBits(speedLimit);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + ( (velocity == null) ? 0 : velocity.hashCode());
//		return result;
//	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Entity other = (Entity) obj;
		if (direction == null)
		{
			if (other.direction != null) return false;
		} else if (!direction.equals(other.direction)) return false;
		if (isTerminated != other.isTerminated) return false;
		if (mass == null)
		{
			if (other.mass != null) return false;
		} else if (!mass.equals(other.mass)) return false;
		if (position == null)
		{
			if (other.position != null) return false;
		} else if (!position.equals(other.position)) return false;
		if (shape == null)
		{
			if (other.shape != null) return false;
		} else if (!shape.equals(other.shape)) return false;
		if (Double.doubleToLongBits(speedLimit) != Double.doubleToLongBits(other.speedLimit)) return false;
		if (velocity == null)
		{
			if (other.velocity != null) return false;
		} else if (!velocity.equals(other.velocity)) return false;
		if (world == null)
		{
			if (other.world != null) return false;
		} else if (!world.equals(other.world)) return false;
		return true;
	}
}
