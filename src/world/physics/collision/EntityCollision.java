package world.physics.collision;

import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import world.physics.vector.Velocity;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * TODO document
 */
public final class EntityCollision extends Collision
{
    /**
     * Creates a new collision between entities.
     * 
     * @param world		The world in which collision occurs.
     * @param entity1	The first entity involved in the collision.
     * @param entity2	The second entity involved in the collision.
     */
    public EntityCollision (World world, Entity entity1, Entity entity2)
    {
	super(world);
	setEntity1(entity1);
	setEntity2(entity2);

	calculateCollisionTime();
	//calculateCollisionPosition ();
    }

    /**
     * Returns the first entity involved in the collision.
     * 
     * @return entity1	The first entity involved in the collision. 
     */
    public Entity getEntity1 ()
    {
	return entity1;
    }

    /**
     * Set the first entity involved involved in the collision.
     * 
     * @param entity1
     */
    public void setEntity1 (Entity entity1)
    {
	this.entity1 = entity1;
    }

    /**
     * Returns the second entity involved in the collision.
     * 
     * @return entity2	The second entity involved in the collision. 
     */
    public Entity getEntity2 ()
    {
	return entity2;
    }

    /**
     * Set the second entity involved involved in the collision.
     * 
     * @param entity2
     */
    private void setEntity2 (Entity entity2)
    {
	this.entity2 = entity2;
    }

    /**
     * Returns whether this entity can be an entity.
     * 
     * @param entity
     * @return
     */
    private boolean canHaveAsEntity (Entity entity)
    {
	return (entity != null);
    }

    /**
     * The first entity involved in the collision.
     */
    private Entity	entity1;

    /**
     * The second entity involved in the collision.
     */
    private Entity	entity2;

    /**
     * @see collision.Collision#resolve()
     */
    @Override
    public void resolve ()
    {
	if (entity1 == entity2) return;
	else this.collideWith(that);
    }


    /**
     * Calculates the time to collision between two entities. //TODO document	
     * 
     * @throws  IllegalArgumentException
     *          One of the given entities is null.
     *          | ((entity1 == null) || (entity2 == null))
     */
    @Override
    protected void calculateCollisionTime () throws IllegalArgumentException
    {
	if ( (entity1 == null) || (entity2 == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

	double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius(); // size difference between entitities
		
	Vector deltaR = entity2.getPosition().getDifference(entity1.getPosition()); // distance between entitites
	Vector deltaV = entity2.getVelocity().getDifference(entity1.getVelocity()); // difference of the velocities
		
	double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ( (deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2))); 
		

	if (deltaV.dotProduct(deltaR) >= 0 || d <= 0) this.timeToCollision = Double.POSITIVE_INFINITY;
	else this.timeToCollision = - (deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
    }

    /**
     * Sets the position at which two entities will collide.
     * 
     * @throws  IllegalArgumentException
     *          One of the given entities is null.
     *          | ((entity1 == null) || (entity1 == null))
     */
    @Override
    protected void calculateCollisionPosition ()
    {
	if ( (entity1 == null) || (entity2 == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

	double deltaT = getTimeToCollision();

	if (Double.isInfinite(deltaT)) { return; }

	Position newPosShip1 = entity1.getPosition().getSum(entity1.getVelocity().getScaledBy(deltaT));
	Position newPosShip2 = entity2.getPosition().getSum(entity2.getVelocity().getScaledBy(deltaT));

	double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
	double ship1Radius = entity1.getShape().getRadius();

	this.collisionPosition = new Position(newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma)));
    }

    //TODO DOCUMENT
    /* (non-Javadoc)
     * @see world.physics.collision.Collision#toString()
     */
    @Override
    public String toString ()
    {
	return "Entity" + super.toString() + " of " + getEntity1() + " and " + getEntity1();
    }
}
