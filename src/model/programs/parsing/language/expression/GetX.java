package model.programs.parsing.language.expression;


public class GetX extends FirstOrderExpressionOfEntityToNumber
{
	public GetX (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getPosition()._X());
	}
}
