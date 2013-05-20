package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;

public class Skip extends Action
{
	public Skip (int line, int column) throws ProgramException
	{
		super(line, column);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		finish();
		return true;
	}

	@Override
	public boolean isTypeSafe ()
	{
		return true;
	}
	
	@Override
	public String toString ()
	{
		return "Skip";
	}
}
