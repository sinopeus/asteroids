package model.programs.parsing.language.expression;

public class BooleanLiteral extends ConstantExpression
{

	public BooleanLiteral (int line, int column, boolean value)
	{
		super(line, column);
		setValue(value);
	}

	boolean	value;

	public boolean getValue ()
	{
		return value;
	}

	public void setValue (boolean value)
	{
		this.value = value;
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		return this;
	}

}
