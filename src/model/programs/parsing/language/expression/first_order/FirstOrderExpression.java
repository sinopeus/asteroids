package model.programs.parsing.language.expression.first_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;

public abstract class FirstOrderExpression extends Expression
{
	protected FirstOrderExpression (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column);
		setArgument(argument);
	}

	Expression	argument;

	protected Expression getArgument ()
	{
		return argument;
	}

	protected static boolean canHaveAsArgument (Expression argument)
	{
		return (argument != null);
	}

	protected void setArgument (Expression argument)
	{
		if (!canHaveAsArgument(argument)) throw new IllegalArgumentException("Invalid argument for first order expression.");
		this.argument = argument;
	}
	
	@Override
	public void setParentProgram (Program parrentProgram) throws IllegalArgumentException
	{
		super.setParentProgram(parrentProgram);
		getArgument().setParentProgram(parrentProgram);
	}
	
	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome firstArgumentIsTypeSafe = getArgument().isTypeSafe();
		if (!firstArgumentIsTypeSafe.isSuccessful()) return TypeCheckOutcome.failure("The first argument of the first order expression " + getLine() + ", " + getColumn() + " is not type safe.");
		return TypeCheckOutcome.success();
	}
	
	@Override
	public String toString ()
	{
		return this.getClass().getSimpleName() + " [argument=" + argument + "]";
	}
}
