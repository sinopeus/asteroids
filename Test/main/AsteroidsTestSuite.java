package main;

import model.FacadeTest;
import model.PartialFacadeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import vector.AccelerationTest;
import vector.DirectionTest;
import vector.PositionTest;
import vector.VectorTest;
import vector.VelocityTest;

@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses(
{ AccelerationTest.class, AngleTest.class, CircleShapeTest.class, DirectionTest.class, FacadeTest.class, PartialFacadeTest.class, PositionTest.class, VectorTest.class, VelocityTest.class,
		ShipTest.class })
public class AsteroidsTestSuite
{
}