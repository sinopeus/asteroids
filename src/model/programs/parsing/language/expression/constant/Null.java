package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.entity.Entity;

public class Null extends ConstantExpression <Entity>
{
	public Null (int line, int column) throws IllegalArgumentException
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
		return new EntityLiteral(getLine(), getColumn(), null);
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_ENTITY;
	}

	@Override
	public String toString ()
	{
		return "Null";
	}
}
