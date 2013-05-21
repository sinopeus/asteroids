package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public abstract class SecondOrderExpressionOfNumbersToNumber extends SecondOrderExpression
{

	protected SecondOrderExpressionOfNumbersToNumber (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return true;
	}

	protected abstract DoubleLiteral function (DoubleLiteral first, DoubleLiteral second);

	@Override
	public boolean isTypeSafe ()
	{
		boolean firstArgumentIsTypeSafe = getFirstArgument().isTypeSafe();
		boolean secondArgumentIsTypeSafe = getSecondArgument().isTypeSafe();
		boolean firstArgumentIsCorrectType = getFirstArgument().getType() == Type.TYPE_DOUBLE;
		boolean secondArgumentIsCorrectType = getSecondArgument().getType() == Type.TYPE_DOUBLE;
		return (firstArgumentIsTypeSafe && secondArgumentIsTypeSafe && firstArgumentIsCorrectType && secondArgumentIsCorrectType);
	}

	@Override
	public Type getType ()
	{
		return Type.TYPE_DOUBLE;
	}

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
	}
}
