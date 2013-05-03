package model.programs.parsing.language.expression;

public class Addition extends SecondOrderExpressionOfNumbersToNumber
{
	public Addition (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return new DoubleLiteral(getLine(), getColumn(), first.getValue() + second.getValue());
	}
}
