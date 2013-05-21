package model.programs.parsing.language.expression;

import model.programs.ProgramException;
import model.programs.parsing.language.ProgramPart;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;

public abstract class Expression extends ProgramPart
{
	protected Expression (int line, int column) throws IllegalArgumentException
	{
		super(line,column);
	}

	public abstract ConstantExpression<?> evaluate ();
	
	public abstract Type getType();
}
