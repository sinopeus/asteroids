package model.programs.parsing.language.expression;

public abstract class SecondOrderExpressionOfNumbersToNumber extends SecondOrderExpression
{

	public SecondOrderExpressionOfNumbersToNumber (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument.evaluate() instanceof DoubleLiteral;
	}

	protected abstract DoubleLiteral function (DoubleLiteral first, DoubleLiteral second);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
	}
}
