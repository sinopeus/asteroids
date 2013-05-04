package model.programs.parsing.language.statement;

import model.programs.parsing.language.expression.ConstantExpression;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.Variable;

public class Assignment extends Statement
{
	public Assignment (int line, int column, Variable variable, Expression value)
	{
		super(line, column);
		setVariable(variable);
		setValue(value);
	}

	Variable	variable;

	public Variable getVariable ()
	{
		return variable;
	}

	protected boolean canHaveAsVariable (Variable variable)
	{
		return variable != null; //TODO more checking?
	}

	protected void setVariable (Variable variable)
	{
		if (!canHaveAsVariable(variable)) throw new IllegalArgumentException("Invalid variable provided for assignment");
		this.variable = variable;
	}

	Expression	value;

	public Expression getValue ()
	{
		return value;
	}

	protected boolean canHaveAsValue (Expression value)
	{
		return (value != null);//TODO more checking?
	}

	protected void setValue (Expression value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value provided for assignment");
		this.value = value;
	}

	protected boolean isTypeSafe ()
	{
		return (getVariable().getType() == getValue().getType());
	}

	@Override
	public boolean executeUntilAction ()
	{
		super.executeUntilAction();
		if (!isTypeSafe()) throw new IllegalArgumentException("Type error."); //TODO other kind of exception?
		getVariable().setValue((ConstantExpression) getValue().evaluate()); //TODO work away cast
		finish();
		return false;
	}
}
