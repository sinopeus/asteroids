package main;

import model.FacadeTest;
import model.PartialFacadeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import vector.AccelerationTest;
import vector.DirectionTest;
import vector.ForceTest;
import vector.PositionTest;
import vector.VectorTest;
import vector.VelocityTest;
import world.WorldTest;
import Utilities.NewtonTest;
import collision.BorderCollisionTest;
import collision.CollisionTest;
import collision.EntityCollisionTest;
import entity.AngleTest;
import entity.AsteroidTest;
import entity.BulletTest;
import entity.CircleShapeTest;
import entity.EntityTest;
import entity.MassTest;
import entity.ship.ShipTest;
import entity.ship.ThrusterTest;

@SuppressWarnings ("javadoc")
@RunWith (Suite.class)
@SuiteClasses (
{ BorderCollisionTest.class, CollisionTest.class, EntityCollisionTest.class,
		MassTest.class, ShipTest.class, ThrusterTest.class, AngleTest.class,
		AsteroidTest.class, BulletTest.class, CircleShapeTest.class,
		EntityTest.class, CollisionResolverTest.class, FacadeTest.class,
		PartialFacadeTest.class, NewtonTest.class, AccelerationTest.class,
		DirectionTest.class, ForceTest.class, PositionTest.class,
		VectorTest.class, VelocityTest.class, WorldTest.class })
public class AsteroidsTestSuite
{
}