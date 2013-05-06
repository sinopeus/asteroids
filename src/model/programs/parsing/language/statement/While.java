package model.programs.parsing.language.statement;

import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.Expression;

public class While extends Statement
{
	public While (int line, int column, Expression condition, Statement body)
	{
		super(line, column);
		setCondition(condition);
		setBody(body);
	}

	Expression	condition;

	public Expression getCondition ()
	{
		return condition;
	}

	protected boolean canHaveAsCondition (Expression condition)
	{
		return (condition != null);//TODO more checking?
	}

	private void setCondition (Expression condition)
	{
		if (!canHaveAsCondition(condition)) throw new IllegalArgumentException("Invalid condition provided for while statement."); //TODO ?
		this.condition = condition;
	}

	private Statement	body;

	public Statement getBody ()
	{
		return body;
	}

	protected boolean canHaveAsBody (Statement body)
	{
		return (body != null); //TODO more checking?
	}

	private void setBody (Statement body)
	{
		this.body = body;
	}

	boolean	resultOfCondition;

	public boolean getResultOfCondition ()
	{
		return resultOfCondition;
	}

	private boolean	checked;

	public boolean isChecked ()
	{
		return checked;
	}

	private void checkCondition ()
	{
		resultOfCondition = ((BooleanLiteral) (getCondition().evaluate())).getValue();
		checked = true;
	}

	private void finishIteration ()
	{
		checked = false;
	}

	public boolean execute ()//TODO check this, TEST IT
	{
		super.execute();
		boolean actionOcurred = false;
		if (!checked) checkCondition();
		if (resultOfCondition)
		{
			actionOcurred = getBody().execute();
			if (actionOcurred) return true;
			else
			{
				finishIteration();
				return execute();
			}
		} else finish();
		return false;
	}
}
