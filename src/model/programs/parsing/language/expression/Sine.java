package model.programs.parsing.language.expression;

public class Sine extends FirstOrderExpressionOfNumberToNumber
{
	public Sine (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), Math.sin(argument.getValue()));
	}

	@Override
	public String toString ()
	{
		return "Sine [argument=" + argument + "]";
	}
}
