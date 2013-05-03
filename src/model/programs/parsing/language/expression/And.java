package model.programs.parsing.language.expression;

public class And extends SecondOrderExpressionOfBooleansToBoolean
{
	public And (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (BooleanLiteral first, BooleanLiteral second)
	{
		return new BooleanLiteral(getLine(), getColumn(), (first.getValue() && second.getValue()));
	}

}
