package model.programs.parsing.language.expression.second_order;

import model.programs.Program;
import model.programs.parsing.language.expression.Expression;

public abstract class SecondOrderExpression extends Expression
{
	public SecondOrderExpression (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column);
		setFirstArgument(firstArgument);
		setSecondArgument(secondArgument);
	}

	Expression	firstArgument;

	public Expression getFirstArgument ()
	{
		return firstArgument;
	}

	protected void setFirstArgument (Expression firstArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid first argument for second order expression.");
		this.firstArgument = firstArgument;
	}

	Expression	secondArgument;

	public Expression getSecondArgument ()
	{
		return secondArgument;
	}

	protected void setSecondArgument (Expression secondArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid second argument for second order expression.");
		this.secondArgument = secondArgument;
	}

	protected boolean canHaveAsArgument (Expression argument)
	{
		return argument != null;
	}
	
	@Override
	public void setParentProgram (Program parentProgram)
	{
		super.setParentProgram(parentProgram);
		getFirstArgument().setParentProgram(parentProgram);
		getSecondArgument().setParentProgram(parentProgram);
	}
}