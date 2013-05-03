package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetX extends FirstOrderExpressionOfEntity
{
	public GetX (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument.getPosition()._X();
	}
}
