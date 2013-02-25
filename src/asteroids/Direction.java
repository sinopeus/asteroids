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
