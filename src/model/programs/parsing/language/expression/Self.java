package model.programs.parsing.language.expression;

import world.entity.Entity;

public class Self extends FirstOrderExpressionOfEntity
{
	public Self (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument;
	}
}
