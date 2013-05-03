package model.programs.parsing.language.expression;

public class Not extends FirstOrderExpresionOfBooleanToBoolean
{
	public Not (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected BooleanLiteral function (BooleanLiteral argument)
	{
		return new BooleanLiteral(getLine(), getColumn(), !argument.getValue());
	}
}
