package entity;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import entity.ship.Ship;
import entity.ship.Mass;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * TODO document
 */
public class Bullet extends Entity
{
	/**
	 * Initializes a new bullet.
   * 
   * @param   ship
   *          The ship which has fired this bullet.
   * @effect  | this.super(ship.getDirection(), ship.getPosition(), ship.getSpeedLimit, initialVelocity, new CircleShape(radius), new Mass(shape.getRadius() * density));
   *            setShooter(ship);
	 */
  public Bullet (Ship ship) throws NullPointerException {
    this.super(ship.getDirection(), ship.getPosition(), ship.getSpeedLimit(), initialVelocity, new CircleShape(radius), new Mass(shape.getRadius() * density));
    setShooter(ship);
  }

	/**
	 * Returns the shooter of this bullet.
   *
   * @return shooter
	 */
	@Basic
	@Raw
	public Ship getShooter()
	{
    return shooter;
	}

	/**
	 * Checks whether this bullet can have the given shooter ship as its shooter ship.
	 * 
	 * @param 	shooter
	 * 			The shooter ship to check.
	 * @return	True if and only if the given shooter ship is not null.
	 * 			| result = (shooter != null)
	 */
	@Basic
	@Raw
	private boolean canHaveAsShooter(Ship shooter)
	{
    return (shooter != null);
  }

	/**
	 * Sets the shooter ship to the given shooter ship.
	 * 
	 * @param 	shooter
	 * 			The given shooter ship.
	 * @post	The shooter ship of this bullet is equal to the given shooter ship.
	 * 			| new.getShooter() == shooter
	 * @throws 	IllegalArgumentException
	 * 			The given shooter is null
	 * 			| shooter == null
	 */
	private void setShooter(Ship shooter) throws IllegalArgumentException
	{
    if (canHaveAsShooter) this.shooter = shooter;
    else throws new IllegalArgumentException("You cannot provide a null ship.");
	}

  /**
   * A variable keeping a reference to the ship which shot this bullet.
   */
	private Ship shooter;

  /**
   * @see #Entity.terminate()
   */
	@Override
	public void terminate()
	{
		super.terminate();
	}

  /**
   * A variable registering the radius of a bullet.
   */
	private static double radius = 3;

  /**
   * A variable registering the initial velocity of a bullet.
   */
	private static double initialVelocity = 250;

  /**
   * A variable registering the density of a bullet.
   */
	private static double density = 7.8E12;
}
