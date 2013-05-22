package model.programs.parsing.language.expression.second_order;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.parsing.language.expression.Expression;

public abstract class SecondOrderExpression extends Expression
{
	protected SecondOrderExpression (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column);
		setFirstArgument(firstArgument);
		setSecondArgument(secondArgument);
	}

	Expression	firstArgument;

	protected Expression getFirstArgument ()
	{
		return firstArgument;
	}

	protected void setFirstArgument (Expression firstArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid first argument for second order expression.");
		this.firstArgument = firstArgument;
	}

	Expression	secondArgument;

	protected Expression getSecondArgument ()
	{
		return secondArgument;
	}

	protected void setSecondArgument (Expression secondArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid second argument for second order expression.");
		this.secondArgument = secondArgument;
	}

	protected static boolean canHaveAsArgument (Expression argument)
	{
		return (argument != null);
	}

	@Override
	public void setParentProgram (Program parentProgram) throws IllegalArgumentException
	{
		super.setParentProgram(parentProgram);
		getFirstArgument().setParentProgram(parentProgram);
		getSecondArgument().setParentProgram(parentProgram);
	}

	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome firstArgumentIsTypeSafe = getFirstArgument().isTypeSafe();
		if (!firstArgumentIsTypeSafe.isSuccessful()) return TypeCheckOutcome.failure("The first argument of the second order expression " + getLine() + ", " + getColumn() + " is not type safe.\n" + toString());
		TypeCheckOutcome secondArgumentIsTypeSafe = getSecondArgument().isTypeSafe();
		if (!secondArgumentIsTypeSafe.isSuccessful()) return TypeCheckOutcome.failure("The second argument of the second order expression " + getLine() + ", " + getColumn() + " is not type safe.\n" + toString());
		return TypeCheckOutcome.success();
	}

	@Override
	public String toString ()
	{
		return this.getClass().getSimpleName() + "[firstArgument=" + firstArgument + ", secondArgument=" + secondArgument + "]";
	}
}
