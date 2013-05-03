package model.programs.parsing.language.expression;

public class True extends ConstantExpression
{
	public True (int line, int column)
	{
		super(line, column);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return new BooleanLiteral(getLine(), getColumn(), true);
	}
}
