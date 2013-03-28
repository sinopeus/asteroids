package world.physics;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import world.physics.vector.Acceleration;
import world.physics.vector.Force;
import world.physics.vector.Velocity;

public class MechanicsTest
{

	@Before
	public void setUpImmutableTestFicture_Vectors ()
	{
		testMass1 = new Mass(2);
		testMass2 = new Mass(6);
		testVelocity1 = new Velocity(1, 2);
		testAcceleration1 = new Acceleration(3, 9);
		testForce1 = new Force(2, 4);
	}

	private static Mass			testMass1, testMass2;
	private static Velocity		testVelocity1;
	private static Acceleration	testAcceleration1;
	private static Force		testForce1;

	@Test
	public void firstLaw_CalculateAccelerationTest_PerfectParameters ()
	{
		assertEquals(Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, testMass2, testAcceleration1), new Acceleration(1, 3));
	}

	@Test (expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalM1 ()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(null, testMass2, testAcceleration1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalM2 ()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, null, testAcceleration1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void firstLaw_CalculateAccelerationTest_IllegalA ()
	{
		Mechanics.Newtons_firstLaw_CalculateAcceleration(testMass1, testMass2, null);
	}

	@Test
	public void secondLaw_CalculateAccelerationTest_PerfectParameters ()
	{
		assertEquals(Mechanics.Newtons_secondLaw_CalculateAcceleration(testForce1, testMass1), new Acceleration(1, 2));
	}

	@Test (expected = IllegalArgumentException.class)
	public void secondLaw_CalculateAccelerationTest_IllegalF1 ()
	{
		Mechanics.Newtons_secondLaw_CalculateAcceleration(null, testMass1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void secondLaw_CalculateAccelerationTest_IllegalF2 ()
	{
		Mechanics.Newtons_secondLaw_CalculateAcceleration(testForce1, null);
	}

	@Test
	public void thirdLaw_CalculateForceTest_PerfectParameters ()
	{
		assertEquals(Mechanics.Newtons_thirdLaw_CalculateForce(testForce1), new Force(-2, -4));
	}

	@Test (expected = IllegalArgumentException.class)
	public void thirdLaw_CalculateForceTest_IllegalForce ()
	{
		Mechanics.Newtons_thirdLaw_CalculateForce(null);
	}

	@Test
	public void ConserVationOfMomentum_CalculateVelocityTest ()
	{
		assertEquals(Mechanics.ConserVationOfMomentum_CalculateVelocity(testVelocity1, testMass1, testMass2), new Velocity(1.0 / 3.0, 2.0 / 3.0));
	}

	@Test (expected = IllegalArgumentException.class)
	public void ConserVationOfMomentum_CalculateVelocityTest_IllegalV1 ()
	{
		Mechanics.ConserVationOfMomentum_CalculateVelocity(null, testMass1, testMass2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void ConserVationOfMomentum_CalculateVelocityTest_IllegalM1 ()
	{
		Mechanics.ConserVationOfMomentum_CalculateVelocity(testVelocity1, null, testMass2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void ConserVationOfMomentum_CalculateVelocityTest_IllegalM2 ()
	{
		Mechanics.ConserVationOfMomentum_CalculateVelocity(testVelocity1, testMass1, null);
	}
	
	@Test
	public void fuckThisShitTest(){
		new Mechanics();
	}
}
