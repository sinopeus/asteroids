package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A wrapper class for angles.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Angle
{

	public double getAngle()
	{
		return this.angle;
	}

	public void setAngle(double angle)
	{
		this.angle = angle;
	}

	private double angle;

	public double sin()
	{
		return Math.sin(angle);
	}

	public double cos()
	{
		return Math.cos(angle);
	}

	public Angle(double angle)
	{
		setAngle(angle % (2 * Math.PI));
	}

	public Angle()
	{
		setAngle(0.0);
	}
}
