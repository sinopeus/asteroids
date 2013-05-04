package model.programs.parsing.language.expression;


public class GetRadius extends FirstOrderExpressionOfEntityToNumber
{
	public GetRadius (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getShape().getRadius());
	}
}