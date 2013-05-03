package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetY extends FirstOrderExpressionOfEntity
{
	public GetY (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument.getPosition()._Y();
	}
}
