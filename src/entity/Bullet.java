package entity;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import entity.ship.Ship;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * TODO document
 */
public class Bullet extends Entity
{
	//TODO make constructor.

	/**
	 * Gets the shooter of this bullet.
	 */
	@Basic
	@Raw
	public Ship getShooter()
	{
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
	private boolean canHaveAsShooter(ship shooter)
	{
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
	}

	private Ship shooter;

	@Override
	public void terminate()
	{
		// TODO Auto-generated method stub
		super.terminate();
	}

	private static double radius = 3;
	private static double initialVelocity = 250;

	private static double massDensity = 7.8 * Math.pow(10, 12);
}
