package model.programs.parsing.language.statement.action;

import world.entity.ship.Ship;

public class ThrustOff extends Action
{
	public ThrustOff (int line, int column)
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
}
