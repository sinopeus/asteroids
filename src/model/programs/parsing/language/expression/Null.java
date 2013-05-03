package model.programs.parsing.language.expression;

public class Null extends ConstantExpression
{
	@Override
	public Object evaluate ()
	{
		return null;
	}
}
