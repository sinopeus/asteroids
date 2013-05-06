package model.programs.parsing.language.expression;

public class BooleanLiteral extends ConstantExpression<Boolean>
{

	public BooleanLiteral (int line, int column, Boolean value)
	{
		super(line, column,value);
	}
}
