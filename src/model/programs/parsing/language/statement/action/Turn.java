package model.programs.parsing.language.statement.action;

import model.programs.parsing.language.expression.DoubleLiteral;
import model.programs.parsing.language.expression.Expression;
import world.entity.ship.Ship;
import world.physics.geometry.Angle;

public class Turn extends Action
{
	public Turn (int line, int column, Expression angle)
	{
		super(line, column);
		setAngle(angle);
	}

	Expression	angle;

	public Expression getAngle ()
	{
		return angle;
	}

	protected boolean canHaveAsAngle (Expression angle)
	{
		return (angle != null);//TODO more checking?
	}

	public void setAngle (Expression angle)
	{
		if (!canHaveAsAngle(angle)) throw new IllegalArgumentException("Invalid angle provided for turn statement.");
		this.angle = angle;
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		double amount = ((DoubleLiteral) (getAngle().evaluate())).getValue();
		getOwnerShip().turn(new Angle(amount));
		finish();
		return true;
	}

	@Override
	public String toString ()
	{
		return "Turn [angle=" + angle + "]";
	}
}
