package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
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
	public boolean isTypeSafe ()
	{
		if (!getFirstArgument().isTypeSafe()) return false;
		if (!getSecondArgument().isTypeSafe()) return false;
		if (getFirstArgument().getType() != getSecondArgument().getType()) return false;
		return ( (getFirstArgument().getType() == Type.TYPE_DOUBLE) || (getFirstArgument().getType() == Type.TYPE_ENTITY));
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
