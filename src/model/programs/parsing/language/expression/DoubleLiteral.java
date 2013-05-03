package model.programs.parsing.language.expression;

public class DoubleLiteral extends ConstantExpression
{

	public DoubleLiteral (int line, int column, double value)
	{
		super(line, column);
		setValue(value);
	}

	Double	value;

	public Double getValue ()
	{
		return value;
	}

	public boolean canHaveAsValue (Double value)
	{
		return true;
	}

	public void setValue (Double value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value for double literal.");
		this.value = value;
	}

	@Override
	public DoubleLiteral evaluate ()
	{
		return this;
	}

}
