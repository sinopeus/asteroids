package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.expression.Expression;


public abstract class ConstantExpression <T> extends Expression
{
	public ConstantExpression (int line, int column, T value)
	{
		super(line, column);
		setValue(value);
	}

	protected T	value;

	public T getValue ()
	{
		return value;
	}

	public boolean canHaveAsValue (T value)
	{
		return true;
	}

	public void setValue (T value)
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