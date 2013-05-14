package model.programs.parsing.language.expression;

import model.programs.parsing.language.expression.constant.ConstantExpression;


public class Variable extends Expression
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

	protected void setName (String name)
	{
		if (!canHaveAsName(name)) throw new IllegalArgumentException("Invalid name for variable.");
		this.name = name;
	}

	@Override
	public ConstantExpression evaluate ()
	{
		return getParentProgram().getVariableNamed(getName());
	}

	@Override
	public String toString ()
	{
		return "Variable [name=" + name + ", value=" + evaluate() + "]";
	}
}
