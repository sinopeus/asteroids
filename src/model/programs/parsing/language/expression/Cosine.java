package model.programs.parsing.language.expression;

public class Cosine extends FirstOrderExpressionOfNumberToNumber
{
	public Cosine (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), (Math.cos(argument.getValue())));
	}

	@Override
	public String toString ()
	{
		return "Cosine [argument=" + argument + "]";
	}
}
