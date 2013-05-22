package model.programs.parsing.language.statement.action;

import model.IFacade.TypeCheckOutcome;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import world.physics.geometry.Angle;

public class Turn extends Action
{
	public Turn (int line, int column, Expression angle) throws IllegalArgumentException
	{
		super(line, column);
		setAngle(angle);
	}

	Expression	angle;

	protected Expression getAngle ()
	{
		return angle;
	}

	protected static boolean canHaveAsAngle (Expression angle)
	{
		return (angle != null);
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
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome angle = getAngle().isTypeSafe();
		if (!angle.isSuccessful()) return angle;
		boolean angleIsNumber = getAngle().getType() == Type.TYPE_DOUBLE;
		if (!angleIsNumber) return TypeCheckOutcome.failure("The angle expression of turn statement at " + getLine() + ", " + getColumn() + " is not a double.");
		return TypeCheckOutcome.success();
	}

	@Override
	public String toString ()
	{
		return "Turn [angle=" + angle + "]";
	}
}
