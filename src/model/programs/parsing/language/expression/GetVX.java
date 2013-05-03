package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetVX extends FirstOrderExpressionOfEntity
{
	public GetVX (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument.getVelocity()._X();
	}
}
