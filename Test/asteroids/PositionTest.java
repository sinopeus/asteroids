package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PositionTest
{
	@Before
	public void setUpImmutableTestFixture_Position()
	{
		testPosition = new Position(5, 5);
	}

	@Test
	public void setXComponentTest_LegalCase()
	{
		testPosition.setXComponent(10);
		assertTrue(Util.fuzzyEquals(testPosition.getXComponent(), 10));
	}

	@Test
	public void setXComponentTest_IllegalCase()
	{

	}

	@Test
	public void setYComponentTest_LegalCase()
	{
		testPosition.setYComponent(10);
		assertTrue(Util.fuzzyEquals(testPosition.getYComponent(), 10));
	}

	@Test
	public void setYComponentTest_IllegalCase()
	{

	}
	
	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents(){
		Position p = new Position(5,6);
		assertTrue(Util.fuzzyEquals(p.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(p.getYComponent(), 6));
	}
	
	@Test
	public void simpleConstructorTest_ComponentsMatchZero(){
		Position p = new Position();
		assertTrue(Util.fuzzyEquals(p.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(p.getYComponent(), 0));
	}

	private static Position testPosition;
}