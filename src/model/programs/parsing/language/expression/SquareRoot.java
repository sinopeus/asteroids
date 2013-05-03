package model.programs.parsing.language.expression;

public class SquareRoot extends FirstOrderExpressionOfNumberToNumber
{
	public SquareRoot (int line,int column,Expression argument)
	{
		super(line,column,argument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral argument)
	{
		return new DoubleLiteral(getLine(),getColumn(),Math.sqrt(argument.getValue()));
	}
}
