package model.programs.parsing.language.expression.second_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public abstract class SecondOrderExpressionOfNumbersToBoolean extends SecondOrderExpression
{

	protected SecondOrderExpressionOfNumbersToBoolean (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	protected abstract BooleanLiteral function (DoubleLiteral first, DoubleLiteral second);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean firstArgumentIsCorrectType = getFirstArgument().getType() == Type.TYPE_DOUBLE;
		if (!firstArgumentIsCorrectType) return TypeCheckOutcome.failure("The first argument of the second order expression of numbers to a boolean at " + getLine() + ", " + getColumn() + " is not a double.\n" + toString());
		boolean secondArgumentIsCorrectType = getSecondArgument().getType() == Type.TYPE_DOUBLE;
		if (!secondArgumentIsCorrectType) return TypeCheckOutcome.failure("The second argument of the second order expression of numbers to a boolean at " + getLine() + ", " + getColumn() + " is not a double.\n" + toString());
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
		return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
	}
}
