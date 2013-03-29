package alternate;

import world.entity.ship.Ship;

public class AlternateShipControl extends Ship
{
	private Ship	ship;

	private double	addingAngle;
	private boolean	firing;

	public AlternateShipControl (Ship ship)
	{
		this.ship = ship;
		this.addingAngle = 0;
		this.firing = false;
	}

	public Ship getShip ()
	{
		return ship;
	}

	public void setShip (Ship ship)
	{
		this.ship = ship;
	}

	public double getAddingAngle ()
	{
		return addingAngle;
	}

	public void setAddingAngle (double addingAngle)
	{
		this.addingAngle = addingAngle;
	}

	public boolean isFiring ()
	{
		return firing;
	}

	public void setFiring (boolean firing)
	{
		this.firing = firing;
	}
}
