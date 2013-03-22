package Utilities;

import vector.Acceleration;
import vector.Force;
import entity.ship.Mass;

/**
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Newton
{
  /**
   * @param m1 The first mass. 
   * @param m2 The second mass.
   * @param a1 The first acceleration.
   * @return The second acceleration, as calculated from the arguments.
   *         | return (a1.getScaledBy(m1.get() / m2.get()))
   * @throws IllegalArgumentException If the provided acceleration provided is equal to zero or any of the arguments are null objects.
   */
  public static Acceleration firstLaw_CalculateAcceleration(Mass m1, Mass m2, Acceleration a1) throws IllegalArgumentException
  {
    return a2;
  }

  /**
   * @param f The given force.
   * @param m The given mass.
   * @return  The acceleration corresponding to this force and mass.
   *          | return (f.getScaledBy(m.get()))
   * @throws IllegalArgumentException If any of the arguments are null objects.
   */
    public static Acceleration secondLaw_CalculateAcceleration(Force f, Mass m) throws IllegalArgumentException
    {
      return a;
    }

  /**
   * @param   f1  The given force.
   * @return  The opposite force.
   *          | return f1.getScaledBy(-1.0)
   */
  public static Force thirdLaw_CalculateForce(Force f1){
    return f2;
  }
}
