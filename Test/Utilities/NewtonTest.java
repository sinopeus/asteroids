package Utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    m1 = new Mass(20);
    m2 = new Mass(10);
    a1 = new Acceleration(3,4);

    firstLaw_CalculateAcceleration(

  }

  @Test
  public void secondLaw_CalculateAccelerationTest()
  {
  }

  @Test
  public void thirdLaw_CalculateForceTest()
  {
  }
}
