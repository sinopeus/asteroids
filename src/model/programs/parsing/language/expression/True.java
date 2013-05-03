package model.programs.parsing.language.expression;

public class True extends ConstantExpression
{
	@Override
	public Object evaluate ()
	{
		return true;
	}
}
