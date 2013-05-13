package model.programs.parsing.language.expression;

import model.programs.Program;

public abstract class FirstOrderExpression extends Expression
{
	public FirstOrderExpression (int line, int column, Expression argument)
	{
		super(line, column);
		setArgument(argument);
	}

	Expression	argument;

	public Expression getArgument ()
	{
		return argument;
	}

	protected boolean canHaveAsArgument (Expression argument)
	{
		return argument != null;
	}

	protected void setArgument (Expression argument)
	{
		if (!canHaveAsArgument(argument)) throw new IllegalArgumentException("Invalid argument for first order expression.");
		this.argument = argument;
	}
	
	@Override
	public void setParentProgram (Program parrentProgram)
	{
		super.setParentProgram(parrentProgram);
		getArgument().setParentProgram(parrentProgram);
	}
}
