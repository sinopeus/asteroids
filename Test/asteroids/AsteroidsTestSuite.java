package asteroids;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ AngleTest.class, CircleShapeTest.class, DirectionTest.class, PositionTest.class, VectorTest.class, VelocityTest.class, ShipTest.class })
public class AsteroidsTestSuite
{

}
