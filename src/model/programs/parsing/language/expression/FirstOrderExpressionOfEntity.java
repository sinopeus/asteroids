package model.programs.parsing.language.expression;

import world.entity.Entity;

public abstract class FirstOrderExpressionOfEntity extends FirstOrderExpression
{
	public FirstOrderExpressionOfEntity (Object argument)
	{
		super(argument);
	}
	
	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if(!super.canHaveAsArgument(argument))return false;
		return argument instanceof Entity;
	}
	
	protected abstract Object function (Entity argument);

	@Override
	public Object evaluate ()
	{
		return function((Entity) getArgument());
	}
}
