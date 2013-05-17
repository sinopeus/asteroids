package model.programs.parsing.language.expression;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.ProgramPart;
import model.programs.parsing.language.expression.constant.ConstantExpression;

public abstract class Expression extends ProgramPart
{
	protected Expression (int line, int column) throws ProgramException
	{
		super(line,column);
	}

	public abstract ConstantExpression evaluate ();
}
