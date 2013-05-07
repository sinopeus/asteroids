package model.programs.parsing.language.statement.action;

import model.programs.parsing.language.statement.Statement;
import world.entity.ship.Ship;

public abstract class Action extends Statement
{

	public Action (int line, int column)
	{
		super(line, column);
	}

	@Override
	public boolean execute (Ship ship)
	{
		return true;
	}
}
