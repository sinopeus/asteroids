package model.programs.parsing.language.statement.action;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.statement.Statement;

public abstract class Action extends Statement
{
	public Action (int line, int column) throws ProgramException
	{
		super(line, column);
	}

	@Override
	public boolean execute ()
	{
		return true;
	}
}
