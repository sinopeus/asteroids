package model.programs.parsing.language.expression;

import world.entity.Entity;
import world.entity.ship.Ship;

public class GetDirection extends ConstantExpression <Double>
{

	public GetDirection (int line, int column)
	{
		super(line, column, null);
	}

	@Override
	public ConstantExpression <Double> evaluate ()
	{
		return new DoubleLiteral(getLine(), getColumn(), getOwnerShip().getDirection().getAngle().get());
	}
}
