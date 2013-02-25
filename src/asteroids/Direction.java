package asteroids;

/**
 * A class for storing information on the orientation of the spaceship.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Direction extends Vector
{

    /**
     * Initializes this new direction with a given angle.
     *
     * @param   angle
     *          The given angle.
     * @post    A unit vector reflecting the direction will be correctly
     *          computed based on the angle provided.
     */
    public Direction(Angle angle)
    {
        setXComponent(angle.cos());
        setYComponent(angle.sin());
    }

    /**
     * {@inheritDoc}
     * @see Vector#Direction()
     */
    public Direction()
    {
        this(new Angle());
    }

}
