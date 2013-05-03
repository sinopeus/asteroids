package model.programs.parsing.language.expression;

public abstract class SecondOrderExpressionOfBooleansToBoolean extends SecondOrderExpression
{

	public SecondOrderExpressionOfBooleansToBoolean (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument.evaluate() instanceof BooleanLiteral;
	}

	protected abstract BooleanLiteral function (BooleanLiteral first, BooleanLiteral second);

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((BooleanLiteral) getFirstArgument().evaluate(), (BooleanLiteral) getSecondArgument().evaluate());
	}
}
