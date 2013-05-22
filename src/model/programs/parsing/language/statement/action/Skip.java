package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;

public class Skip extends Action
{
	public Skip (int line, int column) throws IllegalArgumentException
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
	public String toString ()
	{
		return "Skip";
	}
}
