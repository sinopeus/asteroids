package model.programs.parsing.language.expression;

public class Not extends FirstOrderExpresionOfBoolean
{
	public Not (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Boolean argument)
	{
		return !argument;
	}
}
