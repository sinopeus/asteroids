package asteroids;

public class Facade implements IFacade
{

    /**
     * Returns a new ship with default values.
     *
     * @return  A new ship with default values.
     */
    @Override
    public IShip createShip()
    {
        return new Ship();
    }

    /**
     * Creates a ship using the given properties.
     *
     * @param   x
     *          The x component of the ship's position vector.
     * @param   y
     *          The y component of the ship's position vector.
     * @param   xVelocity
     *          The x component of the ship's velocity vector.
     * @param   yVelocity
     *          The y component of the ship's velocity vector.
     * @param   radius
     *          The ship's radius.
     * @param   angle
     *          The ship's angle.
     * @throws  ModelException
     *          When invalid properties are provided for the ship.
     * @return  A new ship with the given values.
     */
    @Override
    public IShip createShip(double x, double y, double xVelocity, double yVelocity, double radius, double angle) throws ModelException
    {
        Angle a;
        Direction direction;
        Position position;
        CircleShape shape;
        Velocity velocity;
        double speedLimit;

        try
        {
            a = new Angle(angle);
            direction = new Direction(a);
            position = new Position(x, y);
            shape = new CircleShape(radius);
            velocity = new Velocity(xVelocity, yVelocity);
            speedLimit = Velocity.getSpeedOfLight();
        } catch (IllegalArgumentException e)
        {
            throw new ModelException("Invalid arguments for facade.createShip(.a..)");
        }

        return new Ship(direction, position, shape, speedLimit, velocity);
    }

    /**
     * Gets the x component of the ship's position vector.
     *
     * @param   ship
     *          The ship of which we want to get the x component of the position vector.
     * @return  The x component of the ship's position vector.
     */
    @Override
    public double getX(IShip ship)
    {
        return ((Ship) ship).getPosition().getXComponent();
    }

    /**
     * Gets the y component of the ship's position vector.
     *
     * @param   ship
     *          The ship of which we want to get the y component of the position vector.
     * @return  The y component of the ship's position vector.
     */
    @Override
    public double getY(IShip ship)
    {
        return ((Ship) ship).getPosition().getYComponent();
    }

    /**
     * Gets the x component of the ship's velocity vector.
     *
     * @param   ship
     *          The ship of which we want to get the x component of the velocity vector.
     * @return  The x component of the ship's velocity vector.
     */
    @Override
    public double getXVelocity(IShip ship)
    {
        return ((Ship) ship).getVelocity().getXComponent();
    }

    /**
     * Gets the y component of the ship's velocity vector.
     *
     * @param   ship
     *          The ship of which we want to get the y component of the velocity vector.
     * @return  The y component of the ship's velocity vector.
     */
    @Override
    public double getYVelocity(IShip ship)
    {
        return ((Ship) ship).getVelocity().getYComponent();
    }

    /**
     * Gets the ship's radius.
     *
     * @param   ship
     *          The ship whose radius we want to know.
     * @return  The ship's radius.
     */
    @Override
    public double getRadius(IShip ship)
    {
        return ((Ship) ship).getShape().getRadius();
    }

    /**
     * Gets the ship's direction expressed in radians.
     *
     * @param   ship
     *          The ship whose direction we want to know. 
     * @return  The ship's direction in radians.
     */
    @Override
    public double getDirection(IShip ship)
    {
        return ((Ship) ship).getDirection().getAngle().getAngle();
    }

    /**
     * Moves the ship with the current velocity and acceleration for a given time.
     *
     * @param   ship  
     *          The ship we want to move. 
     * @param   dt    
     *          The length of time during which we want to move the ship.
     * @throws  ModelException
     *          When an invalid ship or time is provided.
     */
    @Override
    public void move(IShip ship, double dt) throws ModelException
    {
        if (ship == null)
        {
            throw new ModelException("Invalid ship.");
        }
        if (dt < 0)
        {
            throw new ModelException("Invalid time.");
        }
        ((Ship) ship).move(dt);
    }

    /**
     * Applies thrust in order to accelerate the ship.
     *
     * @param   ship      
     *          The ship to which we apply thrust.
     * @param   amount
     *          The amount of thrust we want to apply.
     * @throws  ModelException
     *          When an invalid ship is provided.
     */
    @Override
    public void thrust(IShip ship, double amount) throws ModelException
    {
        if (ship == null)
        {
            throw new ModelException("Invalid ship.");
        }
        if (amount < 0)
        {
            amount = 0;
        }
        ((Ship) ship).thrust(amount);
    }

    /**
     * Turns the ship by the given angle.
     *
     * @param   ship
     *          The ship we want to turn.
     * @param   angle
     *          The angle by which we want to turn the ship.
     * @throws  ModelException
     *          When an invalid ship is provided.
     */
    @Override
    public void turn(IShip ship, double angle) throws ModelException
    {
        if (ship == null){
            throw new ModelException("Invalid ship.");
        }
        ((Ship) ship).turn(new Angle(angle));
    }

    /**
     * Gets the distance between two ships.
     *
     * @param   ship1
     *          The first ship.
     * @param   ship2
     *          The second ship.
     * @throws  ModelException
     *          When an invalid ship is provided.
     * @return The distance in kilometers between the two ships.
     */
    @Override
    public double getDistanceBetween(IShip ship1, IShip ship2) throws ModelException
    {
        if ((ship1 == null) || (ship2 == null)){
            throw new ModelException("Invalid ship.");
        }
        return ((Ship) ship1).getPosition().distanceTo(((Ship) ship2).getPosition());
    }

    /**
     * Checks if two ships overlap.
     *
     * @param   ship1
     *          The first ship.
     * @param   ship2
     *          The second ship.
     * @throws  ModelException
     *          When an invalid ship is provided.
     * @return  Whether the ships overlap or not.
     */
    @Override
    public boolean overlap(IShip ship1, IShip ship2) throws ModelException
    {
        if ((ship1 == null) || (ship2 == null)){
            throw new ModelException("Invalid ship.");
        }
        return getDistanceBetween(ship1, ship2) - getRadius(ship1) - getRadius(ship2) <= 0;
    }

    /**
     * Gets the time to collision between two ships.
     *
     * @param   ship1
     *          The first ship.
     * @param   ship2
     *          The second ship.
     * @throws  ModelException
     *          When an invalid ship is provided.
     * @return  The time to collision between the two ships.
     */
    @Override
    public double getTimeToCollision(IShip ship1, IShip ship2) throws ModelException
    {
        if ((ship1 == null) || (ship2 == null)){
            throw new ModelException("Invalid ship.");
        }

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

    /**
     * Gets the position at which two ships will collide.
     *
     * @param   ship1
     *          The first ship.
     * @param   ship2
     *          The second ship.
     * @throws  ModelException
     *          When an invalid ship is provided.
     * @return  The position of the place of collision.
     */
    @Override
    public double[] getCollisionPosition(IShip ship1, IShip ship2) throws ModelException
    {
        if ((ship1 == null) || (ship2 == null)){
            throw new ModelException("Invalid ship.");
        }
        
        double deltaT = getTimeToCollision(ship1, ship2);
        if (Double.isInfinite(deltaT))
        {
            return null;
        }
        Position newPosShip1 = ((Ship) ship1).getPosition().getSum(((Ship) ship1).getVelocity().scaleBy(deltaT));
        Position newPosShip2 = ((Ship) ship2).getPosition().getSum(((Ship) ship2).getVelocity().scaleBy(deltaT));
        double sigma = ((Ship) ship1).getShape().getRadius() + ((Ship) ship2).getShape().getRadius();
        double ship1Radius = ((Ship) ship1).getShape().getRadius();
        Vector collisionPos = newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).scaleBy(ship1Radius / sigma));
        double[] result =
        { collisionPos.getXComponent(), collisionPos.getYComponent() };
        return result;
    }
}
