package model.programs.parsing.language.expression;

public class GT extends SecondOrderExpressionOfNumbersToBoolean
{
	public GT (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return new BooleanLiteral(getLine(), getColumn(), (first.getValue() > second.getValue()));
	}
}
