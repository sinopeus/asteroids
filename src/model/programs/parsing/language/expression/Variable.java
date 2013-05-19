package model.programs.parsing.language.expression;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;


public class Variable extends Expression
{

	public Variable (int line, int column, String name) throws ProgramException
	{
		super(line, column);
		setName(name);
	}

	Type type;
	
	
	public Type getType() {
		return type;
	}

	protected void setType(Type type) {
		this.type = type;
	}
	
	protected boolean canHaveAsType (Type type)
	{
		return (type != null); 
	}

	String	name;

	public String getName ()
	{
		return name;
	}
	
	protected void setName (String name)
	{
		if (!canHaveAsName(name)) throw new IllegalArgumentException("Invalid name for variable.");
		this.name = name;
	}
	
	protected boolean canHaveAsName (String name)
	{
		return (name != null); //TODO more checking
	}

	@Override
	public ConstantExpression<?> evaluate ()
	{
		return getParentProgram().getVariableNamed(getName());
	}

	@Override
	public String toString ()
	{
		return "Variable [name=" + name + ", value=" + evaluate() + "]";
	}
}
