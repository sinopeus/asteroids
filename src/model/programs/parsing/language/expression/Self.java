package model.programs.parsing.language.expression;

import world.entity.Entity;

public class Self extends FirstOrderExpressionOfEntityToEntity
{
	public Self (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected Entity function (Entity argument)
	{
		return argument;
	}
}
