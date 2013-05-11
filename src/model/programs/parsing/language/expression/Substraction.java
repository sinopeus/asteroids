package model.programs.parsing.language.expression;

public class Substraction extends SecondOrderExpressionOfNumbersToNumber
{
	public Substraction (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return new DoubleLiteral(getLine(), getColumn(), (first.getValue() - second.getValue()));
	}

	@Override
	public String toString ()
	{
		return "Substraction [firstArgument=" + firstArgument + ", secondArgument=" + secondArgument + "]";
	}
}
