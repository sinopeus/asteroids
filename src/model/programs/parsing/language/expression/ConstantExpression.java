package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public abstract class ConstantExpression <T> extends Expression
{
	public ConstantExpression (int line, int column, T value)
	{
		super(line, column);
		setValue(value);
	}

	T	value;

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
	public ConstantExpression <T> evaluate (Ship ship)
	{
		return this;
	}

	@Override
	public String toString ()
	{
		return "ConstantExpression [value=" + value + "]";
	}
}