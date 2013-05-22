package model.programs.parsing.language.expression.first_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public abstract class FirstOrderExpressionOfBooleanToBoolean extends FirstOrderExpression
{
	protected FirstOrderExpressionOfBooleanToBoolean (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	protected abstract BooleanLiteral function (BooleanLiteral argument);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean argumentIsCorrectType = getArgument().getType() == Type.TYPE_BOOLEAN;
		if (!argumentIsCorrectType) return TypeCheckOutcome.failure("The argument of the first order expression of a boolean to a boolean at " + getLine() + ", " + getColumn() + " is not a boolean.\n" + toString());
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
		return function((BooleanLiteral) getArgument().evaluate());
	}
}
