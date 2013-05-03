package model.programs.parsing.language.expression;

public class Null extends ConstantExpression
{
	public Null (int line, int column)
	{
		super(line, column);
	}

	@Override
	public Object evaluate ()
	{
		return null; //TODO not sure about this
	}
}
