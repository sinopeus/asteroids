package asteroids;

public class Facade implements IFacade
{

	@Override
	public IShip createShip()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity, double yVelocity, double radius, double angle)
	{
		Angle a = new Angle(angle);
		Direction direction = new Direction(a);
		Position position = new Position(x, y);
		CircleShape shape = new CircleShape(radius);
		Velocity velocity = new Velocity(xVelocity, yVelocity);
		double speedLimit = Velocity.getSpeedOfLight();

		return new Ship(direction, position, shape, speedLimit, velocity);
	}

	@Override
	public double getX(IShip ship)
	{
		return ((Ship) ship).getPosition().getXComponent();
	}

	@Override
	public double getY(IShip ship)
	{
		// TODO Auto-generated method stub
		return ((Ship) ship).getPosition().getYComponent();
	}

	@Override
	public double getXVelocity(IShip ship)
	{
		// TODO Auto-generated method stub
		return ((Ship) ship).getVelocity().getXComponent();
	}

	@Override
	public double getYVelocity(IShip ship)
	{
		// TODO Auto-generated method stub
		return ((Ship) ship).getPosition().getYComponent();
	}

	@Override
	public double getRadius(IShip ship)
	{
		return ((Ship) ship).getShape().getRadius();
	}

	@Override
	public double getDirection(IShip ship)
	{
		return ((Ship) ship).getDirection().getAngle().getAngle();
	}

	@Override
	public void move(IShip ship, double dt)
	{
		((Ship) ship).move(dt);
	}

	@Override
	public void thrust(IShip ship, double amount)
	{
		((Ship) ship).thrust(amount);
	}

	@Override
	public void turn(IShip ship, double angle)
	{
		((Ship) ship).turn(new Angle(angle));
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2)
	{
		return ((Ship) ship1).getPosition().distanceTo(((Ship) ship2).getPosition());
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2)
	{
		return getDistanceBetween(ship1, ship2) - getRadius(ship1) - getRadius(ship2) <= 0;
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
