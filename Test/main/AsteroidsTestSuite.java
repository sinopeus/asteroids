package main;

import model.FacadeTest;
import model.PartialFacadeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import entity.AngleTest;
import entity.CircleShapeTest;
import entity.ShipTest;
import entity.vector.AccelerationTest;
import entity.vector.DirectionTest;
import entity.vector.PositionTest;
import entity.vector.VectorTest;
import entity.vector.VelocityTest;


@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses(
{ AccelerationTest.class, AngleTest.class, CircleShapeTest.class, DirectionTest.class, FacadeTest.class, PartialFacadeTest.class, PositionTest.class, VectorTest.class, VelocityTest.class,
		ShipTest.class })
public class AsteroidsTestSuite
{
}