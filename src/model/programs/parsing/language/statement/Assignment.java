package model.programs.parsing.language.statement;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.Variable;

public class Assignment extends Statement
{
	public Assignment (int line, int column, Variable variable, Expression value) throws IllegalArgumentException
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

	protected static boolean canHaveAsVariable (Variable variable)
	{
		return (variable != null);
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

	protected static boolean canHaveAsValue (Expression value)
	{
		return (value != null);
	}

	protected void setValue (Expression value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value provided for assignment");
		this.value = value;
	}

	@Override
	public void setParentProgram (Program parentProgram) throws IllegalArgumentException
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
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome expressionIsTypeSafeOutcome = getValue().isTypeSafe();
		if(!expressionIsTypeSafeOutcome.isSuccessful()) return expressionIsTypeSafeOutcome;
		boolean sameTypes = getValue().getType() == getVariable().getType();
		if (!sameTypes) return TypeCheckOutcome.failure("The assignment at " + getLine() + ", " + getColumn() + " is not type safe.");
		return TypeCheckOutcome.success();
	}
	
	@Override
	public boolean containsAction ()
	{
		return false;
	}

	@Override
	public String toString ()
	{
		return "Assignment [variable=" + variable + ", value=" + value + "]";
	}
}
