package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import vector.AccelerationTest;
import vector.DirectionTest;
import vector.PositionTest;
import vector.VectorTest;
import vector.VelocityTest;
import entity.AngleTest;
import entity.CircleShapeTest;
import entity.ship.ShipTest;

@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses(
{ AccelerationTest.class, AngleTest.class, CircleShapeTest.class, DirectionTest.class, PositionTest.class, VectorTest.class, VelocityTest.class, ShipTest.class })
public class AsteroidsTestSuite
{
}