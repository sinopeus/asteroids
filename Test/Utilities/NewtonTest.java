package Utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import vector.Acceleration;
import vector.Force;
import entity.Mass;

public class NewtonTest
{

	@Before
	public void setUpImmutableTestFicture_Vectors()
	{
		testMass1 = new Mass(2);
		testMass2 = new Mass(6);
		testAcceleration1 = new Acceleration(3, 9);
		testForce1 = new Force(2, 4);
	}

	private static Mass testMass1, testMass2;
	private static Acceleration testAcceleration1;
	private static Force testForce1;

	@Test
	public void firstLaw_CalculateAccelerationTest_PerfectParameters()
	{
		assertEquals(Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, testMass2, testAcceleration1), new Acceleration(1, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalM1()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(null, testMass2, testAcceleration1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalM2()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, null, testAcceleration1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalA()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, testMass2, null);
	}

	@Test
	public void secondLaw_CalculateAccelerationTest_PerfectParameters()
	{
		assertEquals(Mechanics.Newtons_secondLaw_CalculateAcceleration(testForce1, testMass1), new Acceleration(1, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondLaw_CalculateAccelerationTest_IllegalF1()
	{
		Mechanics.Newtons_secondLaw_CalculateAcceleration(null, testMass1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondLaw_CalculateAccelerationTest_IllegalF2()
	{
		Mechanics.Newtons_secondLaw_CalculateAcceleration(testForce1, null);
	}

	public void thirdLaw_CalculateForceTest_PerfectParameters()
	{
		assertEquals(Mechanics.Newtons_thirdLaw_CalculateForce(testForce1), new Force(-1, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void thirdLaw_CalculateForceTest_IllegalForce()
	{
		Mechanics.Newtons_thirdLaw_CalculateForce(null);
	}
}
