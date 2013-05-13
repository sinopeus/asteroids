package model.programs.parsing.language.expression;


public class GetVY extends FirstOrderExpressionOfEntityToNumber
{
	public GetVY (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getVelocity()._Y());
	}
}
