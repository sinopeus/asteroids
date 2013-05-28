package model.programs.parsing.language.expression;

import model.IFacade.TypeCheckOutcome;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;


public class Variable extends Expression
{

	public Variable (int line, int column, String name) throws IllegalArgumentException
	{
		super(line, column);
		setName(name);
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
	
	protected static boolean canHaveAsName (String name)
	{
		return (name != null);
	}

	@Override
	public ConstantExpression<?> evaluate ()
	{
		return getParentProgram().getVariableNamed(getName());
	}
	
	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		if(!getParentProgram().getGlobalValues().keySet().contains(getName()))
			return TypeCheckOutcome.failure("The variable at " + getLine() + ", " + getColumn() + " is not declared.");
		return TypeCheckOutcome.success();
	}
	
	@Override
	public Type getType ()
	{
		return getParentProgram().getVariableNamed(getName()).getType();
	}
	
	@Override
	public String toString ()
	{
		return "Variable [name=" + name + ", value=" + evaluate() + "]";
	}
}
