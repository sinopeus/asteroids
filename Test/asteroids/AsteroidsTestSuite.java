package asteroids;

import model.FacadeTest;
import model.PartialFacadeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses(
{ AccelerationTest.class, AngleTest.class, CircleShapeTest.class, DirectionTest.class, FacadeTest.class, PartialFacadeTest.class, PositionTest.class, VectorTest.class, VelocityTest.class,
		ShipTest.class })
public class AsteroidsTestSuite
{
}