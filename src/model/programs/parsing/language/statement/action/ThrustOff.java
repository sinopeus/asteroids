package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;


public class ThrustOff extends Action
{
	public ThrustOff (int line, int column) throws IllegalArgumentException
	{
		super(line, column);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		getOwnerShip().getThruster().deactivate();
		finish();
		return true;
	}
	
	@Override
	public String toString ()
	{
		return "ThrustOff";
	}
}
