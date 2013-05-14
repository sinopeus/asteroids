package model.programs.parsing.language.expression.constant.literal;

import model.programs.parsing.language.expression.constant.ConstantExpression;

public class DoubleLiteral extends ConstantExpression <Double>
{
	public DoubleLiteral (int line, int column, Double value)
	{
		super(line, column, value);
	}
}
