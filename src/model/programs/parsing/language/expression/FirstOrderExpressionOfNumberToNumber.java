package model.programs.parsing.language.expression;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	public FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument.evaluate() instanceof DoubleLiteral;
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getArgument().evaluate());
	}
}
