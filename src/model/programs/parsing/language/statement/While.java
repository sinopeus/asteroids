package model.programs.parsing.language.statement;

import model.programs.Program;
import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.Expression;

public class While extends Statement
{
	public While (int line, int column, Expression condition, Statement body)
	{
		super(line, column);
		checked = false;
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
	
	@Override
	public void setParentProgram (Program parrentProgram)
	{
		super.setParentProgram(parrentProgram);
		getCondition().setParentProgram(parrentProgram);
		getBody().setParentProgram(parrentProgram);
	}

	private void checkCondition ()
	{
		resultOfCondition = ((BooleanLiteral) (getCondition().evaluate())).getValue();
		checked = true;
	}

	private void finishIteration ()
	{
		getBody().unfinish();
		checkCondition();
	}

	@Override
	public void unfinish ()
	{
		getBody().unfinish();
		super.unfinish();
	}

	public boolean execute ()//TODO check this, TEST IT
	{
		super.execute();
		if (!checked) checkCondition();
		while (getResultOfCondition())
		{
			if (getBody().isFinished())
			{
				finishIteration();
				continue;
			}
			if (getBody().execute()) return true;
			else finishIteration();
		}
		finish();
		return false;

	}
}
