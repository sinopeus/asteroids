package model.programs.parsing.language.expression;

import world.entity.Entity;

public class GetVX extends FirstOrderExpressionOfEntityToNumber
{
	public GetVX (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (Entity argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getVelocity()._X());
	}
}
