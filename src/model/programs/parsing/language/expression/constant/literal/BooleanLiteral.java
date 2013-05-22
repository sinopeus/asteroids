package model.programs.parsing.language.expression.constant.literal;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;

public class BooleanLiteral extends ConstantExpression<Boolean>
{
	public BooleanLiteral (int line, int column, Boolean value) throws IllegalArgumentException
	{
		super(line, column,value);
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public String toString ()
	{
		return "BooleanLiteral [value=" + value + "]";
	}
}
