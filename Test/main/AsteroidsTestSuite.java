package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import world.WorldTest;
import world.entity.AsteroidTest;
import world.entity.BulletTest;
import world.entity.EntityTest;
import world.entity.ship.ShipTest;
import world.entity.ship.ThrusterTest;
import world.physics.MassTest;
import world.physics.MechanicsTest;
import world.physics.collision.BorderCollisionTest;
import world.physics.collision.CollisionTest;
import world.physics.collision.EntityCollisionTest;
import world.physics.geometry.AngleTest;
import world.physics.geometry.CircleShapeTest;
import world.physics.vector.AccelerationTest;
import world.physics.vector.DirectionTest;
import world.physics.vector.ForceTest;
import world.physics.vector.PositionTest;
import world.physics.vector.VectorTest;
import world.physics.vector.VelocityTest;

@SuppressWarnings ("javadoc")
@RunWith (Suite.class)
@SuiteClasses (
{ BorderCollisionTest.class, CollisionTest.class, EntityCollisionTest.class, MassTest.class, ShipTest.class, ThrusterTest.class, AngleTest.class, AsteroidTest.class, BulletTest.class, CircleShapeTest.class, EntityTest.class, MechanicsTest.class, AccelerationTest.class, DirectionTest.class, ForceTest.class, PositionTest.class, VectorTest.class, VelocityTest.class, WorldTest.class })
public class AsteroidsTestSuite
{
	
}