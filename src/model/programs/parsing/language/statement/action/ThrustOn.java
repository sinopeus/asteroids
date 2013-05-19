package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;

public class ThrustOn extends Action
{
	public ThrustOn (int line, int column) throws ProgramException
	{
		super(line, column);
	}
	
	@Override
	public boolean execute ()
	{
		super.execute();
		getOwnerShip().getThruster().activate();
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
		return "ThrustOn";
	}
}
