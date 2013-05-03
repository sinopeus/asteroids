package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetRadius extends FirstOrderExpressionOfEntity
{
	public GetRadius (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Entity argument)
	{
		return argument.getShape().getRadius();
	}
}