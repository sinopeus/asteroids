package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class False extends ConstantExpression <Boolean>
{
	public False (int line, int column) throws IllegalArgumentException
	{
		super(line, column, false);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return new BooleanLiteral(getLine(), getColumn(), false);
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public String toString ()
	{
		return "False";
	}
}
