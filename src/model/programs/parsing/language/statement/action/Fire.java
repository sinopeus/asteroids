package model.programs.parsing.language.statement.action;

import model.programs.ProgramException;


public class Fire extends Action
{
	public Fire (int line, int column) throws ProgramException
	{
		super(line, column);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		getOwnerShip().fire();
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
		return "Fire";
	}
}
