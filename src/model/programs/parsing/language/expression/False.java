package model.programs.parsing.language.expression;

public class False extends ConstantExpression
{
	public False (int line, int column)
	{
		super(line, column);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return new BooleanLiteral(getLine(), getColumn(), false);
	}
}
