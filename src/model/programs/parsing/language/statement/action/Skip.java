package model.programs.parsing.language.statement.action;

import world.entity.ship.Ship;

public class Skip extends Action
{
	public Skip (int line, int column)
	{
		super(line, column);
	}

	@Override
	public boolean execute (Ship ship)
	{
		return true;
	}
}
