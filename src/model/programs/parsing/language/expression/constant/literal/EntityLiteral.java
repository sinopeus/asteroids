package model.programs.parsing.language.expression.constant.literal;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import world.entity.Entity;

public class EntityLiteral extends ConstantExpression <Entity>
{
	public EntityLiteral (int line, int column, Entity entity) throws ProgramException
	{
		super(line, column, entity);
	}

	@Override
	public String toString ()
	{
		return "EntityLiteral [value=" + value + "]";
	}
}
