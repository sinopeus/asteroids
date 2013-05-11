package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public class True extends ConstantExpression <Boolean>
{
	public True (int line, int column)
	{
		super(line, column, true);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return new BooleanLiteral(getLine(), getColumn(), true);
	}

	@Override
	public String toString ()
	{
		return "true";
	}
}
