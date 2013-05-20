package model.programs.parsing.language.expression.constant.literal;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import world.entity.Entity;

public class EntityLiteral extends ConstantExpression <Entity>
{
	public EntityLiteral (int line, int column, Entity entity) throws ProgramException
	{
		super(line, column, entity);
	}

	@Override
	protected boolean canHaveAsValue (Entity value)
	{
		return true;
	}
	
	@Override
	public boolean isTypeSafe ()
	{
		return true;
	}

	@Override
	public Type getType ()
	{
		return Type.TYPE_ENTITY;
	}

	@Override
	public String toString ()
	{
		return "EntityLiteral [value=" + value + "]";
	}
}
