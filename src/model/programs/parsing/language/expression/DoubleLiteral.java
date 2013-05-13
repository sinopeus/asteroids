package model.programs.parsing.language.expression;


public class DoubleLiteral extends ConstantExpression <Double>
{

	public DoubleLiteral (int line, int column, Double value)
	{
		super(line, column, value);
	}
}
