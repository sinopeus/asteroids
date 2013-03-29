package alternate;

import world.entity.ship.Ship;

public class AlternateShipControl extends Ship
{
	public Ship		ship;

	public double	addingAngle;
	public boolean	firing;

	public AlternateShipControl (Ship ship)
	{
		this.ship = ship;
		this.addingAngle = 0;
		this.firing = false;
	}
}
