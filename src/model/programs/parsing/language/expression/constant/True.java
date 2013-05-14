package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class True extends ConstantExpression <Boolean>
{
	public True (int line, int column)
	{
		super(line, column, true);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return new BooleanLiteral(getLine(), getColumn(), true);
	}

	@Override
	public String toString ()
	{
		return "True";
	}
}
