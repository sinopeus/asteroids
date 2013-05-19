package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import world.physics.geometry.Angle;

public class Turn extends Action
{
	public Turn (int line, int column, Expression angle) throws ProgramException
	{
		super(line, column);
		setAngle(angle);
	}

	Expression	angle;

	protected Expression getAngle ()
	{
		return angle;
	}

	protected boolean canHaveAsAngle (Expression angle)
	{
		return (angle != null);//TODO more checking?
	}

	protected void setAngle (Expression angle)
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
	public boolean isTypeSafe ()
	{
		boolean angleIsTypeSafe = getAngle().isTypeSafe();
		boolean angleIsNumber = getAngle().getType() == Type.TYPE_DOUBLE;
		return (angleIsTypeSafe && angleIsNumber);
	}

	@Override
	public String toString ()
	{
		return "Turn [angle=" + angle + "]";
	}
}
