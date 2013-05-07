package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public class False extends ConstantExpression <Boolean>
{
	public False (int line, int column)
	{
		super(line, column, false);
	}

	@Override
	public BooleanLiteral evaluate (Ship ship)
	{
		return new BooleanLiteral(getLine(), getColumn(), false);
	}

	@Override
	public String toString ()
	{
		return "false";
	}
}
