package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;

public abstract class ConstantExpression <T> extends Expression
{
	protected ConstantExpression (int line, int column, T value) throws ProgramException
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
		return (value != null);
	}

	protected void setValue (T value) throws ProgramException
	{
		if (!canHaveAsValue(value)) throw new ProgramException(getLine(), getColumn(), "Invalid value: " + value + " for literal.");
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
		return this.getClass().getSimpleName() + " [value=" + value + "]";
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ConstantExpression other = (ConstantExpression) obj;
		if (value == null)
		{
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}
}