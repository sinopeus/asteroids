package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetDirection extends FirstOrderExpressionOfEntityToNumber
{

	public GetDirection (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (Entity argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getDirection().getAngle().get());
	}
}
