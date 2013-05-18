package world.entity.ship;

import java.util.ArrayList;

import model.IShip;
import model.programs.Program;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.physics.Mass;
import world.physics.Mechanics;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ships involving its position, velocity, direction, speed limit and shape.
 *
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 *
 * @invar	The thruster of a ship is a valid thruster
 * 			| canHaveAsThruster(getThruster)
 */
public class Ship extends Entity implements IShip
{
	/**
	 * Initializes this new ship with a given direction, position, shape, mass, speed limit and velocity.
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
	 * @effect	The direction of this ship is set to the given direction.
	 * 			| setDirection(direction)
	 * @effect	The position of this ship is set to the given position.
	 * 			| setPosition(position)
	 * @effect	The speed limit of this ship is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @effect	The velocity of this ship is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this ship is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this ship is set to the given mass.
	 * 			| setMass(mass)
	 * @throws	IllegalArgumentException
	 * 			| The given shape is not a legal shape.
	 * @throws	NullPointerException
	 * 			| Any of the parameters is null.
	 */
	public Ship (Direction direction, Position position, double speedLimit, Velocity velocity, CircleShape shape, Mass mass) throws IllegalArgumentException, NullPointerException
	{
		super(direction, position, speedLimit, velocity, shape, mass);
		setThruster(new Thruster(getThrustPerSecond(), this));
		setBulletList(new ArrayList <Bullet>(3));
	}

	/**
	 * Initializes this new ship with default values.
	 * 
	 * @effect	Initializes this new ship with the extended ship constructor and default values.
	 * 			| this(new Direction(),new Position(),Velocity.getSpeedOfLight(),new Velocity(), new CircleShape(40), new Mass(5E15))
	 */
	public Ship ()
	{
		this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(40), new Mass(5E15));
		setBulletList(new ArrayList <Bullet>(3));
	}

	/**
	 * @see world.entity.Entity#canHaveAsShape(world.physics.geometry.CircleShape)
	 */
	@Override
	protected boolean canHaveAsShape (@Raw CircleShape shape)
	{
		return (super.canHaveAsShape(shape) && (shape.getRadius() >= Ship.getMinimumRadius()));
	}

	/**
	 * Returns the minimum radius of ships.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public static double getMinimumRadius ()
	{
		return Ship.minimumRadius;
	}

	/**
	 * A variable registering the minimum radius of this ship.
	 */
	private static double	minimumRadius	= 10;

	/**
	 * Gets the thruster of this ship.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Thruster getThruster ()
	{
		return thruster;
	}

	/**
	 * Checks whether this ship can have the given thruster as its thruster.
	 * 
	 * @param	thruster
	 * 			The given thruster
	 * @return	True if and only if the given thruster is not null.
	 * 			| result == (thruster != null)
	 */
	private boolean canHaveAsThruster (Thruster thruster)
	{
		return (thruster != null);
	}

	/**
	 * Sets the thrust of this ship to the given thruster.
	 * 
	 * @param	thruster
	 * 			The given thruster.
	 * @throws	IllegalArgumentException
	 * 			The given thruster is null.
	 * 			| thruster == null
	 */
	private void setThruster (Thruster thruster) throws IllegalArgumentException
	{
		if (!canHaveAsThruster(thruster)) { throw new IllegalArgumentException("Invalid thruster provided."); }
		this.thruster = thruster;
	}

	/**
	 * A variable referencing the thruster of this ship.
	 */
	private Thruster	thruster;

	/**
	 * @return the bullets
	 */
	public ArrayList <Bullet> getBulletList ()
	{
		return bulletList;
	}

	/**
	 * TODO document and test
	 * @param bullets
	 * @return
	 */
	private boolean canHaveAsBulletList (ArrayList <Bullet> bullets)
	{
		return (bullets != null);
	}

	/**
	 * @param bullets the bullets to set
	 */
	private void setBulletList (ArrayList <Bullet> bullets)
	{
		if (!canHaveAsBulletList(bullets)) throw new IllegalArgumentException("Invalid bulletlist provided.");
		this.bulletList = bullets;
	}

	private ArrayList <Bullet>	bulletList;

	Program						program;

	//TODO document
	public Program getProgram ()
	{
		return program;
	}

	protected boolean canHaveAsProgram (Program program)
	{
		return true; //TODO more checking?
	}

	public void setProgram (Program program)
	{
		if (!canHaveAsProgram(program)) throw new IllegalArgumentException("Invalid program provided."); //TODO is this necessary
		this.program = program;
		program.setOwner(this);
	}

	/**
	 * Terminates this ship.
	 */
	@Override
	public void terminate ()
	{
		getThruster().terminate();
		super.terminate();
	}

	/**
	 * @see world.entity.Entity#advance(double)
	 */
	@Override
	public void advance (double dt)
	{
		//Add error when terminated.
		if (getThruster().isActivated()) getThruster().thrust(dt);
		super.advance(dt);
		//TODO check order of execution

		if (getProgram() == null) return;
		double gameTimeStart = getWorld().getGameTime();
		int pastIterations = (int) (gameTimeStart / SPEED_OF_ACTIONS);
		double now = gameTimeStart + dt;
		int requiredIterations = (int) (now / SPEED_OF_ACTIONS);
		for (int i = 0; i < requiredIterations - pastIterations; i++){
			getProgram().executeUntilAfterNextAction();
		}
	}

	public boolean canFire ()
	{
		return (getBulletList().size() < MAXIMUM_AMOUNT_OF_BULLETS);
	}
	
	/**
	 * A method for firing a bullet from this ship.
	 * 
	 * @effect	Creates a new Bullet object in the World to which this Ship belongs; the bullet also creates recoil.
	 * 			| getWorld().add(new Bullet(this)) && getVelocity() = getVelocity() - bullet.getVelocity() * (bullet.mass / ship.mass)
	 */
	public void fire ()
	{
		if (!canFire()) return;
		Bullet b = new Bullet(this);
		if (getWorld().add(b))
		;
		{
			this.getBulletList().add(b);

			//RECOIL
			Velocity recoil = Mechanics.conservationOfMomentum_CalculateVelocity(b.getVelocity(), b.getMass(), this.getMass());
			this.setVelocity(new Velocity(getVelocity().getDifference(recoil)));
		}
	}

	/**
	 * Has this Ship collide with the given Asteroid.
	 * 
	 * @param 	that
	 * 			The given Asteroid to collide with.
	 * @effect	terminates the given bullet.
	 * 			| this.terminate()
	 */
	@Override
	public void collideWith (Asteroid that)
	{
		if (that == null) return;
		this.terminate();
	}

	/**
	 * Has this Ship collide with the given Bullet.
	 * 
	 * @param 	that
	 * 			The given Bullet to collide with.
	 * @effect	If this ship shot the given bullet or the bullet is null, don't do anything. Otherwise Terminate both this Ship and the given bullet.
	 * 			| if (that != null && shooter != this) that.terminate() && this.terminate()
	 */
	@Override
	public void collideWith (Bullet that)
	{
		if (that == null) return;
		if (that.getShooter() == this) return;
		this.terminate();
		that.terminate();
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "Ship_" + hashCode() + super.toString();
	}

	/**
	 * Gets the thrust per second of this ship.
	 */
	@SuppressWarnings ("javadoc")
	public double getThrustPerSecond ()
	{
		return Ship.thrustPerSecond / 1000; //This needs to be divided by 1000 because our unit of distance is 1000
	}

	/**
	 * A variable registering the thrust that a ship's thruster can exert in one second.
	 */
	private static double	thrustPerSecond				= 1.1E21;

	/**
	 * TODO
	 */
	private static byte		MAXIMUM_AMOUNT_OF_BULLETS	= 3;

	//TODO document
	private static double	SPEED_OF_ACTIONS			= 2E-1;
}
