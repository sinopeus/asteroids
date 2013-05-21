package model.programs.parsing.language.expression.second_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public abstract class SecondOrderExpressionOfBooleansToBoolean extends SecondOrderExpression
{

	protected SecondOrderExpressionOfBooleansToBoolean (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return true; //TODO
	}

	protected abstract BooleanLiteral function (BooleanLiteral first, BooleanLiteral second);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean firstArgumentIsCorrectType = getFirstArgument().getType() == Type.TYPE_BOOLEAN;
		if (!firstArgumentIsCorrectType) return TypeCheckOutcome.failure("The first argument of the second order expression of booleans to a boolean at " + getLine() + ", " + getColumn() + " is not a boolean.");
		boolean secondArgumentIsCorrectType = getSecondArgument().getType() == Type.TYPE_BOOLEAN;
		if (!firstArgumentIsCorrectType) return TypeCheckOutcome.failure("The second argument of the second order expression of booleans to a boolean at " + getLine() + ", " + getColumn() + " is not a boolean.");
		return TypeCheckOutcome.success();
	}

	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((BooleanLiteral) getFirstArgument().evaluate(), (BooleanLiteral) getSecondArgument().evaluate());
	}
}
