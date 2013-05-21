package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.entity.Entity;

public class Self extends ConstantExpression <Entity>
{
	public Self (int line, int column) throws IllegalArgumentException //TODO not sure about how to do this.
	{
		super(line, column, null);
	}

	@Override
	protected boolean canHaveAsValue (Entity value)
	{
		return true;
	}

	@Override
	public ConstantExpression <Entity> evaluate ()
	{
		return new EntityLiteral(getLine(), getColumn(), getOwnerShip());
	}

	@Override
	public Type getType ()
	{
		return Type.TYPE_ENTITY;
	}

	@Override
	public String toString ()
	{
		return "Self";
	}
}
