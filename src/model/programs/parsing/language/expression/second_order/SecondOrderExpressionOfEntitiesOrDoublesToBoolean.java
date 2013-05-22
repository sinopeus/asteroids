package model.programs.parsing.language.expression.second_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public abstract class SecondOrderExpressionOfEntitiesOrDoublesToBoolean extends SecondOrderExpression
{
	protected SecondOrderExpressionOfEntitiesOrDoublesToBoolean (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	protected abstract BooleanLiteral function (DoubleLiteral first, DoubleLiteral second);

	protected abstract BooleanLiteral function (EntityLiteral first, EntityLiteral second);

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome superIsSafe = super.isTypeSafe();
		if (!superIsSafe.isSuccessful()) return superIsSafe;
		boolean sameType = (getFirstArgument().getType() == getSecondArgument().getType());
		if (!sameType) return TypeCheckOutcome.failure("The arguments of the second order expression at " + getLine() + ", " + getColumn() + " are not of the same type.\n" + toString());
		Type actualType = getFirstArgument().getType();
		if (actualType != Type.TYPE_DOUBLE && actualType != Type.TYPE_ENTITY) return TypeCheckOutcome.failure("The arguments of the second order expression at " + getLine() + ", " + getColumn() + " are of the wrong type.\n" + toString());
		boolean firstArgumentIsCorrectType = getFirstArgument().getType() == actualType;
		if (!firstArgumentIsCorrectType) return TypeCheckOutcome.failure("The first argument of the second order expression of booleans to a boolean at " + getLine() + ", " + getColumn() + " is not of the correct type.\n" + toString());
		boolean secondArgumentIsCorrectType = getSecondArgument().getType() == actualType;
		if (!secondArgumentIsCorrectType) return TypeCheckOutcome.failure("The second argument of the second order expression of booleans to a boolean at " + getLine() + ", " + getColumn() + " is not of the correct type.\n" + toString());
		return TypeCheckOutcome.success();
	}

	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public ConstantExpression <?> evaluate ()
	{
		switch (getFirstArgument().getType())
		{
			case TYPE_DOUBLE:
				return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
			case TYPE_ENTITY:
				return function((EntityLiteral) getFirstArgument().evaluate(), (EntityLiteral) getSecondArgument().evaluate());
			default:
				throw new Error("This should never ever happen.");
		}
	}
}
