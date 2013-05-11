package model.programs.parsing.language.expression;

import model.programs.parsing.language.ProgramPart;
import world.entity.ship.Ship;

public abstract class Expression extends ProgramPart
{
	protected Expression (int line, int column)
	{
		super(line,column);
	}

	public abstract ConstantExpression evaluate ();
}
