package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.expression.Expression;

public abstract class ConstantExpression <T> extends Expression
{
	protected ConstantExpression (int line, int column, T value)
	{
		super(line, column);
		setValue(value);
	}

	protected T	value;

	public T getValue ()
	{
		return value;
	}

	protected boolean canHaveAsValue (T value)
	{
		return true;
	}

	protected void setValue (T value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value for literal.");
		this.value = value;
	}

	@Override
	public ConstantExpression <T> evaluate ()
	{
		return this;
	}

	@Override
	public String toString ()
	{
		return "ConstantExpression [value=" + value + "]";
	}
}