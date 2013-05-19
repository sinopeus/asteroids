package model.programs.parsing.language.statement;

import model.programs.Program;
import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.Variable;

public class Assignment extends Statement
{
	public Assignment (int line, int column, Variable variable, Expression value) throws ProgramException
	{
		super(line, column);
		setVariable(variable);
		setValue(value);
	}

	Variable	variable;

	protected Variable getVariable ()
	{
		return variable;
	}

	protected boolean canHaveAsVariable (Variable variable)
	{
		if (variable == null) return false; //TODO more checking?
		
		return true;
	}

	protected void setVariable (Variable variable)
	{
		if (!canHaveAsVariable(variable)) throw new IllegalArgumentException("Invalid variable provided for assignment");
		this.variable = variable;
	}

	Expression	value;

	protected Expression getValue ()
	{
		return value;
	}

	protected boolean canHaveAsValue (Expression value)
	{
		if (value == null) return false;//TODO more checking?
		Type type = value;
		
		return true;
	}

	protected void setValue (Expression value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value provided for assignment");
		this.value = value;
	}
	
	@Override
	public void setParentProgram (Program parentProgram) throws ProgramException
	{
		super.setParentProgram(parentProgram);
		getVariable().setParentProgram(parentProgram);
		getValue().setParentProgram(parentProgram);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		getParentProgram().setVariableValue(getVariable().getName(), getValue().evaluate());
		finish();
		return false;
	}

	@Override
	public String toString ()
	{
		return "Assignment [variable=" + variable + ", value=" + value + "]";
	}
}
