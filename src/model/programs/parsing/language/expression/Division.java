package model.programs.parsing.language.expression;

public class Division extends SecondOrderExpressionOfNumbersToNumber
{
	public Division (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return new DoubleLiteral(getLine(), getColumn(), (first.getValue() / second.getValue()));
	}
}
