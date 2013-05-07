package model.programs.parsing.language.expression;

import world.entity.Entity;
import world.entity.ship.Ship;

public class Self extends ConstantExpression <Entity>
{

	public Self (int line, int column) //TODO not sure about how to do this.
	{
		super(line, column, null);
	}
	
	@Override
	public ConstantExpression <Entity> evaluate (Ship ship)
	{
		return new EntityLiteral(getLine(),getColumn(), ship);
	}
}
