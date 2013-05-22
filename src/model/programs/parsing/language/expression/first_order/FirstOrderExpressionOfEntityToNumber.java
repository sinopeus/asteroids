package model.programs.parsing.language.expression.first_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public abstract class FirstOrderExpressionOfEntityToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfEntityToNumber (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	protected abstract DoubleLiteral function (EntityLiteral argument);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean argumentIsCorrectType = getArgument().getType() == Type.TYPE_ENTITY;
		if (!argumentIsCorrectType) return TypeCheckOutcome.failure("The argument of the first order expression of an entity to a number at " + getLine() + ", " + getColumn() + " is not an entity.\n" + toString());
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
		return function((EntityLiteral) getArgument().evaluate());
	}
}
