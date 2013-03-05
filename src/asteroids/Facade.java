package asteroids;

public class Facade implements IFacade
{

	@Override
	public IShip createShip()
	{
		return new Ship();
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
		//Zie opgave voor uitleg.
		double sigma = ((Ship) ship1).getShape().getRadius() + ((Ship) ship2).getShape().getRadius();
		Vector deltaR = ((Ship) ship2).getPosition().getDifference(((Ship) ship1).getPosition());
		Vector deltaV = ((Ship) ship2).getVelocity().getDifference(((Ship) ship1).getVelocity());
		double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ((deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2)));
		if (deltaV.dotProduct(deltaR) >= 0 || d <= 0)
		{
			return Double.POSITIVE_INFINITY;
		} else
		{
			return -(deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
		}
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2)
	{
		double deltaT = getTimeToCollision(ship1, ship2);
		if (Double.isInfinite(deltaT))
		{
			return null;
		}
		Position newPosShip1 = ((Ship) ship1).getPosition().getSum(((Ship) ship1).getVelocity().scaleBy(deltaT));
		Position newPosShip2 = ((Ship) ship2).getPosition().getSum(((Ship) ship2).getVelocity().scaleBy(deltaT));
		Vector colisionPos = newPosShip1.getSum(newPosShip2).getDifference(newPosShip1).scaleBy(((Ship) ship1).getShape().getRadius()); // FIXME
		double[] result =
		{ colisionPos.getXComponent(), colisionPos.getYComponent() };
		return result;
	}
}
