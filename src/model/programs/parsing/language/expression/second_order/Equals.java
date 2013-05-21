package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import Utilities.Util;

public class Equals extends SecondOrderExpressionOfEntitiesOrDoublesToBoolean
{

	public Equals (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return null;
	}

	@Override
	protected BooleanLiteral function (EntityLiteral first, EntityLiteral second)
	{
		return null;
	}
}
