package model.programs.parsing.language.statement.action;

import world.entity.ship.Ship;

public class ThrustOn extends Action
{
	public ThrustOn (int line, int column)
	{
		super(line, column);
	}
	
	@Override
	public boolean execute (Ship ship)
	{
		super.execute(ship);
		ship.getThruster().activate();
		finish();
		return true;
	}
}
