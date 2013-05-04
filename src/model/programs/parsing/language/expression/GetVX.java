package model.programs.parsing.language.expression;

public class GetVX extends FirstOrderExpressionOfEntityToNumber
{
	public GetVX (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getVelocity()._X());
	}
}
