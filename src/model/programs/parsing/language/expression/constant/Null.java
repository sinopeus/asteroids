package model.programs.parsing.language.expression.constant;

public class Null extends ConstantExpression <Object>
{
	public Null (int line, int column)
	{
		super(line, column, null);
	}

	@Override
	protected boolean canHaveAsValue (Object value)
	{
		return true;
	}

	@Override
	public String toString ()
	{
		return "Null";
	}
}
