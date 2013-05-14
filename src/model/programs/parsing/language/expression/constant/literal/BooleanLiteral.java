package model.programs.parsing.language.expression.constant.literal;

import model.programs.parsing.language.expression.constant.ConstantExpression;

public class BooleanLiteral extends ConstantExpression<Boolean>
{
	public BooleanLiteral (int line, int column, Boolean value)
	{
		super(line, column,value);
	}

	@Override
	public String toString ()
	{
		return "BooleanLiteral [value=" + value + "]";
	}
}
