package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public abstract class SecondOrderExpressionOfBooleansToBoolean extends SecondOrderExpression
{

	protected SecondOrderExpressionOfBooleansToBoolean (int line, int column, Expression firstArgument, Expression secondArgument) throws ProgramException
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
	public boolean isTypeSafe ()
	{
		boolean firstArgumentIsTypeSafe = getFirstArgument().isTypeSafe();
		boolean secondArgumentIsTypeSafe = getSecondArgument().isTypeSafe();
		boolean firstArgumentIsCorrectType = getFirstArgument().getType() == Type.TYPE_BOOLEAN;
		boolean secondArgumentIsCorrectType = getSecondArgument().getType() == Type.TYPE_BOOLEAN;
		return (firstArgumentIsTypeSafe && secondArgumentIsTypeSafe && firstArgumentIsCorrectType && secondArgumentIsCorrectType);
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
