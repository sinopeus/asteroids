package model.programs.parsing.language.expression;

public abstract class FirstOrderExpresionOfBoolean extends FirstOrderExpression
{
	public FirstOrderExpresionOfBoolean (Object argument)
	{
		super(argument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Double;
	}

	protected abstract Object function (Boolean argument);

	@Override
	public Object evaluate ()
	{
		return function((Boolean) getArgument());
	}
}
