package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.ProgramException;
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
		if (!canHaveAsValue(value)) throw new ProgramException(getLine(),getColumn(),"Invalid value for literal.");
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