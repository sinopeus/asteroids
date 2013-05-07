package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public class Variable <T extends ConstantExpression <U>, U> extends Expression
{

	public Variable (int line, int column, String name)
	{
		super(line, column);
		setName(name);
	}

	String	name;

	public String getName ()
	{
		return name;
	}

	protected boolean canHaveAsName (String name)
	{
		return (name != null); //TODO more checking
	}

	private void setName (String name)
	{
		if (!canHaveAsName(name)) throw new IllegalArgumentException("Invalid name for variable.");
		this.name = name;
	}

	T	value;

	private U getValue ()
	{
		return value.getValue();
	}

	protected boolean canHaveAsValue (T value)
	{
		return (value != null); //TODO some more checking?
	}

	public void setValue (T value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value for variable.");
		this.value = value;
	}

	public Class getType ()
	{
		return getValue().getClass();
	}

	@Override
	public ConstantExpression evaluate (Ship ship)
	{
		return value;
	}

	@Override
	public String toString ()
	{
		return "Variable [name=" + name + ", value=" + value + "]";
	}
}
