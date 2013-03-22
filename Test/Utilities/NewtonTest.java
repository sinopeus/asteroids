package Utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import model.Util;

import org.junit.Before;
import org.junit.Test;

import vector.Acceleration;
import vector.Mass;
import vector.Vector;

public class NewtonTest
{

  Mass m1, m2;
  Acceleration a1, a2;
  Force f1, f2;

  @Test
  public void firstLaw_CalculateAccelerationTest()
  {
    m1 = new Mass(2);
    m2 = new Mass(6);
    a1 = new Acceleration(3,9);
    a2 = new Acceleration(1,3);

    assertEquals(firstLaw_CalculateAcceleration(m1, m2, a1), a2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstLaw_CalculateAccelerationTest()
  {
    m2 = new Mass(6);
    a1 = new Acceleration(3,9);
    a2 = new Acceleration(1,3);

    firstLaw_CalculateAcceleration(null, m2, a1);

  }

  @Test
  public void secondLaw_CalculateAccelerationTest()
  {
    f1 = new Force(2,4);
    m1 = new Mass(2);
    a1 = new Acceleration(1,2);

    assertEquals(secondLaw_CalculateAcceleration(f1, m1), a1);
  }

  @Test
  public void thirdLaw_CalculateForceTest()
  {
    f1 = new Force(1,-2);
    f2 = new Force(-1,2);

    assertEquals(thirdLaw_CalculateForce(f1), 2);

  }
}
