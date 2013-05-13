package model.programs.parsing.language.expression;


public class GetY extends FirstOrderExpressionOfEntityToNumber
{
	public GetY (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getPosition()._Y());
	}
}
