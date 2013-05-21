package model.programs.parsing.language.expression.constant.literal;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;

public class DoubleLiteral extends ConstantExpression <Double>
{
	public DoubleLiteral (int line, int column, Double value) throws IllegalArgumentException
	{
		super(line, column, value);
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_DOUBLE;
	}

	@Override
	public String toString ()
	{
		return "DoubleLiteral [value=" + value + "]";
	}
}
