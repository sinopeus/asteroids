package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.entity.Entity;

public class Self extends ConstantExpression <Entity>
{

	public Self (int line, int column) //TODO not sure about how to do this.
	{
		super(line, column, null);
	}
	
	@Override
	public ConstantExpression <Entity> evaluate ()
	{
		return new EntityLiteral(getLine(),getColumn(), getOwnerShip());
	}
}
