package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetVY extends FirstOrderExpressionOfEntity
{
	public GetVY (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument.getVelocity()._Y();
	}
}
