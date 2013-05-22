package model.programs.parsing.language.expression.first_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean argumentIsCorrectType = getArgument().getType() == Type.TYPE_DOUBLE;
		if (!argumentIsCorrectType) return TypeCheckOutcome.failure("The argument of the first order expression of a number to a number at " + getLine() + ", " + getColumn() + " is not a number.\n" + toString());
		return TypeCheckOutcome.success();
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_DOUBLE;
	}
	
	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getArgument().evaluate());
	}
}
