package model.programs.parsing.language.expression;

public class False extends ConstantExpression
{
	@Override
	public Object evaluate ()
	{
		return false;
	}
}
